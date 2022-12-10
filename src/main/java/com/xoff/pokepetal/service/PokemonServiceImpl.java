package com.xoff.pokepetal.service;

import com.xoff.pokepetal.model.Pokemon;
import com.xoff.pokepetal.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Transactional
    public Pokemon save(Pokemon pokemon) {

        return pokemonRepository.saveAndFlush(pokemon);
    }
    public List<Pokemon> getAll()
    {

        return pokemonRepository.findAll();
    }
    @Transactional
    public List<Pokemon> saveAll(List<Pokemon> listPokemon) {

        return pokemonRepository.saveAll(listPokemon);

    }
}
