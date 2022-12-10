package com.xoff.pokepetal.service;

import com.xoff.pokepetal.model.Pokemon;
import com.xoff.pokepetal.repository.PokePetalRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokePetalServiceImpl  implements PokePetalService {

    private PokePetalRepository pokePetalRepository;

    @Transactional
    public Pokemon save(Pokemon pokemon){
        return pokemon;
    }

    @Transactional
    public List<Pokemon> saveAll (List<Pokemon> listPokemon){
        return listPokemon;
    }
}
