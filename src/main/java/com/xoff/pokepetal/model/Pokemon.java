package com.xoff.pokepetal.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "CONTENT", unique=true, insertable=true, updatable=true, nullable=false)
    private  String content;

}
