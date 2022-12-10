package com.xoff.pokepetal.business;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PokemonBusiness {

    private Long id;
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
