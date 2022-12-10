package com.xoff.pokepetal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POKEMON")
@Data
@NoArgsConstructor
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POKEMON_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "TYPE1")
    private String type1;
    @Column(name = "TYPE2")
    private String type2;
    @Column(name = "TOTAL")
    private int total;
    @Column(name = "HP")
    private int hp;
    @Column(name = "ATTACK")
    private int attack;
    @Column(name = "DEFENSE")
    private int defense;
    @Column(name = "SOATK")
    private int soAtk;
    @Column(name = "SPDEF")
    private int spDef;
    @Column(name = "SPEED")
    private int speed;
    @Column(name = "GENERATION")
    private int generation;
    @Column(name = "LEGENDARY", unique = false, insertable = true, updatable = true, nullable = false)
    private boolean legendary;

}
