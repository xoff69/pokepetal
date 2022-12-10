package com.xoff.pokepetal.repository;

import com.xoff.pokepetal.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    @Override
    List<Pokemon> findAll();

    @Override
    Pokemon saveAndFlush(Pokemon entity);

}