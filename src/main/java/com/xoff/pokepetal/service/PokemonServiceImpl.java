package com.xoff.pokepetal.service;

import com.xoff.pokepetal.dto.PagePokemonDto;
import com.xoff.pokepetal.dto.PokemonDto;
import com.xoff.pokepetal.dto.PokemonMapper;
import com.xoff.pokepetal.exception.BusinessException;
import com.xoff.pokepetal.model.Pokemon;
import com.xoff.pokepetal.repository.PokemonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return PokemonMapper.INSTANCE.mapListEntity2Dto(pokemonRepository.saveAll(PokemonMapper.INSTANCE.mapListDto2Entity(listPokemonDto)));
    }

    @Override
    public PokemonDto findPokemonById(Long id) throws BusinessException {
        Optional<Pokemon> pokemonFound = pokemonRepository.findById(id);
        if (Boolean.FALSE.equals(pokemonFound.isPresent())) {
            throw new BusinessException("Pokemon Not Found", "Aucun Pokemon avec l'identifiant :" + id);
        }
        return PokemonMapper.INSTANCE.pokemon2PokemonDto(pokemonFound.get());
    }

    @Transactional
    @Override
    public void deletePokemon(Long id) throws BusinessException {
        try {
            pokemonRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            log.error(String.format("Aucun Pokemon n'existe avec l'identifiant: " + id, ex));
            throw new BusinessException("DeleteUserError", "Erreur de suppression du Pokemon avec l'identifiant: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            throw new BusinessException("DeleteUserError", "Erreur de suppression du Pokemonavec l'identifiant: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public PokemonDto saveOrUpdate(PokemonDto pokemonDto) throws BusinessException {
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
        PagePokemonDto pagePokemonDto = new PagePokemonDto();
        pagePokemonDto.setTotalPages(pagePokemon.getTotalPages());
        pagePokemonDto.setNumber(pagePokemon.getNumber());
        pagePokemonDto.setTotalElements(pagePokemon.getTotalElements());
        pagePokemonDto.setListPokemonsDto(PokemonMapper.INSTANCE.mapListEntity2Dto(pagePokemon.getContent()));
        return pagePokemonDto;
    }
}
