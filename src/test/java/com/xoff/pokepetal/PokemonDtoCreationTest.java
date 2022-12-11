package com.xoff.pokepetal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PokemonDtoCreationTest {
    private String name;
    private String type1;
    private String type2;
    private int total;
    private int hp;

    private int attack;
    private int defense;
    private int soAtk;

    private int spDef;

    private int speed;
    private int generation;
    private boolean legendary;
}
