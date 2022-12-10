package com.xoff.pokepetal;

import com.xoff.pokepetal.controller.PokemonController;
import com.xoff.pokepetal.dto.PokemonDto;
import com.xoff.pokepetal.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(PokemonController.class)
public class PokemonControllerTest {

    @MockBean
    PokemonService pokemonService;

    @Autowired
    MockMvc mockMvc;



    @Test
    public void testGetByID() throws Exception {
        long idPokemon=23;

        PokemonDto pokemon = new PokemonDto();
        pokemon.setId(idPokemon);
        pokemon.setName("Bob");
        pokemon.setType1("t1");
        pokemon.setType2("t2");

        Mockito.when(pokemonService.findPokemonById(idPokemon)).thenReturn(pokemon);

        mockMvc.perform(get("/pokemons/"+idPokemon))
                .andExpect(status().isFound()).andExpect(content().string(containsString("Bob")));

        mockMvc.perform(get("/pokemons/"+(idPokemon+1)))
                .andExpect(status().isNotFound());
    }


}
