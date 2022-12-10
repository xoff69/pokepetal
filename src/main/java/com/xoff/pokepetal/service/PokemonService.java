package com.xoff.pokepetal.service;

import com.xoff.pokepetal.dto.PagePokemonDto;
import com.xoff.pokepetal.dto.PokemonDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PokemonService {


    PokemonDto create(PokemonDto pokemonDto);

    PokemonDto update(Long id, PokemonDto pokemonDto);

    PagePokemonDto findAll(Pageable paging);

    List<PokemonDto> saveAll(List<PokemonDto> listPokemonDto);

    PokemonDto findPokemonById(Long id);

    boolean deletePokemon(Long id);
}
