package com.xoff.pokepetal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xoff.pokepetal.controller.PokemonController;
import com.xoff.pokepetal.dto.PagePokemonDto;
import com.xoff.pokepetal.dto.PokemonDto;
import com.xoff.pokepetal.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PokemonController.class)
public class PokemonControllerTest {

    @MockBean
    PokemonService pokemonService;

    @Autowired
    MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetByPage() throws Exception {
        // creation du jeu de tests
        int nbpokemon = 1000;
        List<PokemonDto> pokemonDtoList = new ArrayList();
        for (long i = 0; i < nbpokemon; i++) {
            PokemonDto pokemon = new PokemonDto();
            pokemon.setId(i);
            pokemon.setName("Bob" + i);
            pokemon.setType1("t1");
            pokemon.setType2("t2");
            pokemonDtoList.add(pokemon);
        }

        PagePokemonDto pagePokemonDto = new PagePokemonDto();
        pagePokemonDto.setTotalElements(nbpokemon);
        pagePokemonDto.setNumber(5);
        pagePokemonDto.setTotalPages(nbpokemon / 10);
        pagePokemonDto.setListPokemonsDto(pokemonDtoList);
        Pageable paging = PageRequest.of(5, 20);

        Mockito.when(pokemonService.findAll(paging)).thenReturn(pagePokemonDto);

        mockMvc.perform(get("/pokemons?page=5&size=20")).andExpect(status().isOk()).andExpect(content().string(containsString("Bob242")));


    }

    @Test
    public void testGetByID() throws Exception {
        long idPokemon = 23;

        PokemonDto pokemon = new PokemonDto();
        pokemon.setId(idPokemon);
        pokemon.setName("Bob");
        pokemon.setType1("t1");
        pokemon.setType2("t2");

        Mockito.when(pokemonService.findPokemonById(idPokemon)).thenReturn(pokemon);

        mockMvc.perform(get("/pokemons/" + idPokemon)).andExpect(status().isOk()).andExpect(content().string(containsString("Bob")));

        mockMvc.perform(get("/pokemons/" + (idPokemon + 1))).andExpect(status().isNotFound());
    }

    @Test
    public void testDelete() throws Exception {
        long idPokemon = 23;

        PokemonDto pokemon = new PokemonDto();
        pokemon.setId(idPokemon);
        pokemon.setName("Bob");
        pokemon.setType1("t1");
        pokemon.setType2("t2");

        Mockito.when(pokemonService.deletePokemon(idPokemon)).thenReturn(true);

        mockMvc.perform(delete("/pokemons/" + idPokemon)).andExpect(status().isNoContent());

        mockMvc.perform(get("/pokemons/" + (idPokemon + 1))).andExpect(status().isNotFound());
    }

    @Test
    public void testSaveAndUpdate() throws Exception {
        long idPokemon = 23;

        PokemonDto pokemonCreate = new PokemonDto();
        pokemonCreate.setId(0L);
        pokemonCreate.setName("Bob");
        pokemonCreate.setType1("t1");
        pokemonCreate.setType2("t2");

        PokemonDto pokemonCreatedOrUpdate = new PokemonDto();
        pokemonCreatedOrUpdate.setId(idPokemon);
        pokemonCreatedOrUpdate.setName("Bob");
        pokemonCreatedOrUpdate.setType1("t1");
        pokemonCreatedOrUpdate.setType2("t2");

        Mockito.when(pokemonService.create(pokemonCreate)).thenReturn(pokemonCreatedOrUpdate);
        Mockito.when(pokemonService.update(idPokemon, pokemonCreatedOrUpdate)).thenReturn(pokemonCreatedOrUpdate);
        Mockito.when(pokemonService.findPokemonById(idPokemon)).thenReturn(pokemonCreatedOrUpdate);

        mockMvc.perform(post("/pokemons").content(asJsonString(pokemonCreate)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
        mockMvc.perform(put("/pokemons/" + idPokemon).content(asJsonString(pokemonCreatedOrUpdate)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        mockMvc.perform(post("/pokemons").content(asJsonString(pokemonCreatedOrUpdate)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());

    }
}
