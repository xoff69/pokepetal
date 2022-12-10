package com.xoff.pokepetal.controller;

import com.xoff.pokepetal.model.Pokemon;
import com.xoff.pokepetal.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;
    @GetMapping("/pokemons")
    public ResponseEntity<Collection<Pokemon>> getAllPokemons() {
        Collection<Pokemon> pokemons = pokemonService.getAll();
        return new ResponseEntity<Collection<Pokemon>>(pokemons, HttpStatus.FOUND);
    }
}

