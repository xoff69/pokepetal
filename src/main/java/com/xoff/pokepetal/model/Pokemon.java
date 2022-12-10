package com.xoff.pokepetal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "POKEMON")
@Data
@NoArgsConstructor
public class Pokemon {
    @Id
    @Column(name = "POKEMON_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "NAME", unique = true, insertable = true, updatable = true, nullable = false)
    private String name;

    @Column(name = "TYPE1", unique = false, insertable = true, updatable = true, nullable = false)
    private String type1;
    @Column(name = "TYPE2", unique = false, insertable = true, updatable = true, nullable = false)
    private String type2;
    @Column(name = "TOTAL", unique = false, insertable = true, updatable = true, nullable = false)
    private int total;
    @Column(name = "HP", unique = false, insertable = true, updatable = true, nullable = false)
    private int hp;
    @Column(name = "ATTACK", unique = false, insertable = true, updatable = true, nullable = false)
    private int attack;
    @Column(name = "DEFENSE", unique = false, insertable = true, updatable = true, nullable = false)
    private int defense;
    @Column(name = "SOATK", unique = false, insertable = true, updatable = true, nullable = false)
    private int soAtk;
    @Column(name = "SPDEF", unique = false, insertable = true, updatable = true, nullable = false)
    private int spDef;
    @Column(name = "SPEED", unique = false, insertable = true, updatable = true, nullable = false)
    private int speed;
    @Column(name = "GENERATION", unique = false, insertable = true, updatable = true, nullable = false)
    private int generation;
    @Column(name = "LEGENDARY", unique = false, insertable = true, updatable = true, nullable = false)
    private boolean legendary;

}
