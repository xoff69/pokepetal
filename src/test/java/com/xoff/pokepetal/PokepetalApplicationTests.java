package com.xoff.pokepetal;

import com.xoff.pokepetal.controller.PokemonController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PokepetalApplicationTests {

    @Autowired
    PokemonController pokemonController;

    @Test
    void contextLoads() {
        Assertions.assertThat(pokemonController).isNotNull();
    }

}
