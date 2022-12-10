package com.xoff.pokepetal.dto;

import com.xoff.pokepetal.model.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PokemonMapper {
    PokemonMapper INSTANCE = Mappers.getMapper(PokemonMapper.class);

    PokemonDto pokemon2PokemonDto(Pokemon pokemon);

    List<Pokemon> mapListDto2Entity(List<PokemonDto> pokemonsDto);

    Pokemon pokemonDto2Pokemon(PokemonDto pokemonDto);

    List<PokemonDto> mapListEntity2Dto(List<Pokemon> pokemons);
}
