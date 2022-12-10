package com.xoff.pokepetal;

import com.xoff.pokepetal.model.Pokemon;
import com.xoff.pokepetal.repository.PokemonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class PokemonServiceTest {

    @Autowired
    PokemonRepository pokemonRepository;

    @Test
    public void testCreateFindUpdateDeleteFind() {

        Pokemon pokemon = new Pokemon();
        pokemon.setName("Albert");
        pokemon = pokemonRepository.save(pokemon);
        Assertions.assertThat(pokemon.getId() != null);
        Assertions.assertThat(pokemonRepository.findById(pokemon.getId()) != null);
        pokemon.setName("Alberto");
        pokemon = pokemonRepository.save(pokemon);
        Assertions.assertThat("Alberto".equals(pokemon.getName()));

        Pageable paging = PageRequest.of(5, 20);

        Assertions.assertThat(pokemonRepository.findAll(paging).getContent().size() == 1);
        pokemonRepository.deleteById(pokemon.getId());
        Assertions.assertThat(pokemonRepository.findAll(paging).getContent().size() == 0);
    }
}
