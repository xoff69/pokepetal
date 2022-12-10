package com.xoff.pokepetal.service;

import com.xoff.pokepetal.dto.PageMapper;
import com.xoff.pokepetal.dto.PagePokemonDto;
import com.xoff.pokepetal.dto.PokemonDto;
import com.xoff.pokepetal.dto.PokemonMapper;
import com.xoff.pokepetal.model.Pokemon;
import com.xoff.pokepetal.repository.PokemonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;


    @Transactional
    @Override
    public List<PokemonDto> saveAll(List<PokemonDto> listPokemonDto) {
        return PokemonMapper.INSTANCE.mapListEntity2Dto(pokemonRepository.saveAll(PokemonMapper.INSTANCE.map(listPokemonDto)));
    }

    @Override
    public PokemonDto findPokemonById(Long id) {
        Optional<Pokemon> pokemonFound = pokemonRepository.findById(id);
        if (Boolean.FALSE.equals(pokemonFound.isPresent())) {
            return null;
        } else {
            return PokemonMapper.INSTANCE.pokemon2PokemonDto(pokemonFound.get());
        }
    }

    @Transactional
    @Override
    public boolean deletePokemon(Long id) {

        PokemonDto pokemonDto = findPokemonById(id);
        if (pokemonDto != null) {
            pokemonRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Transactional
    @Override
    public PokemonDto create(PokemonDto pokemonDto) {
        Pokemon pokemonToSave = PokemonMapper.INSTANCE.pokemonDto2Pokemon(pokemonDto);

        Pokemon pokemonSaved = pokemonRepository.saveAndFlush(pokemonToSave);
        return PokemonMapper.INSTANCE.pokemon2PokemonDto(pokemonSaved);
    }


    @Transactional
    @Override
    public PokemonDto update(Long id, PokemonDto pokemonDto) {
        PokemonDto pokemonToUpdate = findPokemonById(id);
        if (pokemonToUpdate == null) {
            return null;
        }
        Pokemon pokemonToSave = PokemonMapper.INSTANCE.pokemonDto2Pokemon(pokemonDto);
        pokemonToSave.setId(id);
        Pokemon pokemonSaved = pokemonRepository.saveAndFlush(pokemonToSave);
        return PokemonMapper.INSTANCE.pokemon2PokemonDto(pokemonSaved);

    }

    public PagePokemonDto findAll(Pageable paging) {
        Page<Pokemon> pagePokemon = pokemonRepository.findAll(paging);
        PagePokemonDto pagePokemonDto = PageMapper.INSTANCE.page2PageDto(pagePokemon);
        pagePokemonDto.setListPokemonsDto(PokemonMapper.INSTANCE.mapListEntity2Dto(pagePokemon.getContent()));
        return pagePokemonDto;
    }
}
