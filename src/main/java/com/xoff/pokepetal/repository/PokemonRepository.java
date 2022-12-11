package com.xoff.pokepetal.repository;

import com.xoff.pokepetal.model.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    @Override
    Page<Pokemon> findAll(Pageable pageable);

    @Override
    Pokemon save(Pokemon pokemon);

    @Override
    void deleteById(Long id);

}