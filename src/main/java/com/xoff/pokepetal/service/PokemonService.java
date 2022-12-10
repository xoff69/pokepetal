package com.xoff.pokepetal.service;

import com.xoff.pokepetal.model.Pokemon;

import java.util.List;

public interface PokemonService {
    Pokemon save(Pokemon pokemon);

    List<Pokemon> saveAll(List<Pokemon> listPokemon);
}
