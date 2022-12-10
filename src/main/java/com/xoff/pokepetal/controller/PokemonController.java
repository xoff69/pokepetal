package com.xoff.pokepetal.controller;


import com.xoff.pokepetal.model.Pokemon;
import com.xoff.pokepetal.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {

    @Autowired
    private PokemonService pokePetalService;
    @GetMapping("/greeting")
    public Pokemon get(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Pokemon();
    }
}

