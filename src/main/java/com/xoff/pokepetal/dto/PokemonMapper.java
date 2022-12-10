package com.xoff.pokepetal.dto;

import com.xoff.pokepetal.model.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PokemonMapper {
    PokemonMapper INSTANCE = Mappers.getMapper(PokemonMapper.class);

    PokemonDto pokemon2PokemonBusiness(Pokemon pokemon);

    List<Pokemon> map(List<PokemonDto> employees);

    Pokemon pokemonBusiness2Pokemon(PokemonDto pokemonBusiness);
}
