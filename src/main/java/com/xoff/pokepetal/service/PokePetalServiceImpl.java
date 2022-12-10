package com.xoff.pokepetal.service;

import com.xoff.pokepetal.model.Pokemon;
import com.xoff.pokepetal.repository.PokePetalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokePetalServiceImpl  implements PokePetalService {

    @Autowired
    private PokePetalRepository pokePetalRepository;

    @Transactional
    public Pokemon save(Pokemon pokemon){

        return pokePetalRepository.saveAndFlush(pokemon);
    }

    @Transactional
    public List<Pokemon> saveAll (List<Pokemon> listPokemon){
        return listPokemon;
    }
}
