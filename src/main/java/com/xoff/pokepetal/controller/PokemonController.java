package com.xoff.pokepetal.controller;

import com.xoff.pokepetal.dto.PagePokemonDto;
import com.xoff.pokepetal.dto.PokemonDto;
import com.xoff.pokepetal.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/pokemons")
    public ResponseEntity<Map<String, Object>> getAllPokemons(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        try {

            Pageable paging = PageRequest.of(page, size);
            PagePokemonDto pagePokemonsDto = pokemonService.findAll(paging);

            Map<String, Object> response = new HashMap<>();
            response.put("pokemons", pagePokemonsDto.getListPokemonsDto());
            response.put("currentPage", pagePokemonsDto.getNumber());
            response.put("totalItems", pagePokemonsDto.getTotalElements());
            response.put("totalPages", pagePokemonsDto.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/pokemons")
    public ResponseEntity<PokemonDto> savePokemon(@RequestBody PokemonDto pokemonDto) {

        PokemonDto pokemonDtoSaved = pokemonService.saveOrUpdate(pokemonDto);
        return new ResponseEntity<PokemonDto>(pokemonDtoSaved, HttpStatus.CREATED);
    }

    @PutMapping(value = "/pokemons")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto) {

        PokemonDto pokemonDtoUpdated = pokemonService.saveOrUpdate(pokemonDto);
        return new ResponseEntity<PokemonDto>(pokemonDtoUpdated, HttpStatus.OK);
    }

    @GetMapping(value = "/pokemons/{id}")
    public ResponseEntity<PokemonDto> findPokemonById(@PathVariable(value = "id") Long id) {
        PokemonDto pokemonDtoFound = pokemonService.findPokemonById(id);
        return new ResponseEntity<PokemonDto>(pokemonDtoFound, HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/pokemons")
    public ResponseEntity<Void> deletePokemon(@RequestParam(value = "id", required = true) Long id) {

        pokemonService.deletePokemon(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }
}
