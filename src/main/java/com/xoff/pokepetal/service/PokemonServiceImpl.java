package com.xoff.pokepetal.service;

import com.xoff.pokepetal.dto.PageMapper;
import com.xoff.pokepetal.dto.PagePokemonDto;
import com.xoff.pokepetal.dto.PokemonDto;
import com.xoff.pokepetal.dto.PokemonMapper;
import com.xoff.pokepetal.exception.BusinessException;
import com.xoff.pokepetal.model.Pokemon;
import com.xoff.pokepetal.repository.PokemonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

            PokemonDto pokemonDto=findPokemonById(id);
            if (pokemonDto!=null) {
                pokemonRepository.deleteById(id);
                return true;
            }else{
                return false;
            }

    }

    @Transactional
    @Override
    public PokemonDto saveOrUpdate(PokemonDto pokemonDto) {
        try {

            Pokemon pokemonToSave = PokemonMapper.INSTANCE.pokemonDto2Pokemon(pokemonDto);
            Pokemon pokemonSaved = pokemonRepository.save(pokemonToSave);
            return PokemonMapper.INSTANCE.pokemon2PokemonDto(pokemonSaved);
        } catch (Exception ex) {
            log.error("Erreur technique de création ou de mise à jour d'un pokemom " + pokemonDto.getName(), ex);
            throw new BusinessException("saveOrUpdate", "Erreur technique de création ou de mise à jour d'un pokemon: " + pokemonDto.getName(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public PagePokemonDto findAll(Pageable paging) {
        Page<Pokemon> pagePokemon = pokemonRepository.findAll(paging);
        PagePokemonDto pagePokemonDto = PageMapper.INSTANCE.page2PageDto(pagePokemon);
        pagePokemonDto.setListPokemonsDto(PokemonMapper.INSTANCE.mapListEntity2Dto(pagePokemon.getContent()));
        return pagePokemonDto;
    }
}
