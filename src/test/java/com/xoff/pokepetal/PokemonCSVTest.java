package com.xoff.pokepetal;

import com.xoff.pokepetal.dto.PokemonDto;
import com.xoff.pokepetal.exception.PokemonCSVReaderException;
import com.xoff.pokepetal.util.PokemonCSVUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PokemonCSVTest {

    @Value("${application.seed.dataurl}")
    private String dataUrl;

    @Test
    public void testCSV() throws Exception {
        List<PokemonDto> listPokemonDto = PokemonCSVUtil.loadListPokemonsFromCSVUrl(dataUrl);
        Assertions.assertThat(listPokemonDto != null);
        Assertions.assertThat(listPokemonDto.size() > 0);
        Assertions.assertThat(listPokemonDto.get(0).getName() != null);


        Assertions.assertThatExceptionOfType(PokemonCSVReaderException.class).isThrownBy(() -> {
            PokemonCSVUtil.loadListPokemonsFromCSVUrl("http://www.yahoo.fr");
        });

    }

}
