package com.xoff.pokepetal.service;

import com.xoff.pokepetal.dto.PagePokemonDto;
import com.xoff.pokepetal.dto.PokemonDto;
import com.xoff.pokepetal.exception.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PokemonService {


    PokemonDto saveOrUpdate(PokemonDto pokemonDto) throws BusinessException;

    PagePokemonDto findAll(Pageable paging);

    List<PokemonDto> saveAll(List<PokemonDto> listPokemonDto);

    PokemonDto findPokemonById(Long id) throws BusinessException;

    void deletePokemon(Long id) throws BusinessException;
}
