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

        Pageable paging = PageRequest.of(page, size);
        PagePokemonDto pagePokemonsDto = pokemonService.findAll(paging);
        Map<String, Object> response = new HashMap<>();

        response.put("pokemons", pagePokemonsDto.getListPokemonsDto());
        response.put("currentPage", pagePokemonsDto.getNumber());
        response.put("totalItems", pagePokemonsDto.getTotalElements());
        response.put("totalPages", pagePokemonsDto.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/pokemons")
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        if (pokemonDto.getId() != null && pokemonDto.getId() != 0) {
            PokemonDto pokemonDtoSearch = pokemonService.findPokemonById(pokemonDto.getId());
            if (pokemonDtoSearch!=null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        // does not exist or id = null
        PokemonDto pokemonDtoSaved = pokemonService.create(pokemonDto);
        return new ResponseEntity<PokemonDto>(pokemonDtoSaved, HttpStatus.CREATED);

    }

    @PutMapping(value = "/pokemons/{id}")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable Long id) {
        PokemonDto pokemonDtoUpdated = pokemonService.update(id, pokemonDto);
        if (pokemonDtoUpdated != null) {
            return new ResponseEntity<PokemonDto>(pokemonDtoUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<PokemonDto>(pokemonDtoUpdated, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/pokemons/{id}")
    public ResponseEntity<PokemonDto> findPokemonById(@PathVariable(value = "id") Long id) {

        PokemonDto pokemonDtoFound = pokemonService.findPokemonById(id);
        if (pokemonDtoFound != null) {
            return new ResponseEntity<PokemonDto>(pokemonDtoFound, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/pokemons/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {

        if (pokemonService.deletePokemon(id)) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

