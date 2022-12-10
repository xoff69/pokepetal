package com.xoff.pokepetal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "POKEMON")
@Getter
@Setter
@NoArgsConstructor
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POKEMON_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", unique=true, insertable=true, updatable=true, nullable=false)
    private String name;
    /*
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
*/
}
