package com.xoff.pokepetal.business;

import com.xoff.pokepetal.model.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PokemonMapper {
    PokemonMapper INSTANCE = Mappers.getMapper(PokemonMapper.class);

    PokemonBusiness pokemon2PokemonBusiness(Pokemon pokemon);

    List<Pokemon> map(List<PokemonBusiness> employees);

    Pokemon pokemonBusiness2Pokemon(PokemonBusiness pokemonBusiness);
}
