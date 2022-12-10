package com.xoff.pokepetal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PagePokemonDto {
    private int number;
    private long totalElements;
    private int totalPages;

    private List<PokemonDto> listPokemonsDto;
}
