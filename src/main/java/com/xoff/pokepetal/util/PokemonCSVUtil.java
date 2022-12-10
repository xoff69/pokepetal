package com.xoff.pokepetal.util;

import com.xoff.pokepetal.dto.PokemonDto;
import com.xoff.pokepetal.exception.PokemonCSVReaderException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PokemonCSVUtil {

    private static final String[] HEADERS = {"#", "Name", "Type 1", "Type 2", "Total", "HP", "Attack", "Defense", "Sp. Atk", "Sp. Def", "Speed", "Generation", "Legendary"};

    public static List<PokemonDto> loadListPokemonsFromCSVUrl(String dataUrl) throws PokemonCSVReaderException {
        List<PokemonDto> listPokemon = new ArrayList<>();
        try {
            final URL url = new URL(dataUrl);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(reader);

            for (CSVRecord record : records) {

                PokemonDto pokemonDto = new PokemonDto();
                pokemonDto.setId(Long.parseLong(record.get(HEADERS[0])));
                pokemonDto.setName(record.get(HEADERS[1]));
                pokemonDto.setType1(record.get(HEADERS[2]));
                pokemonDto.setType2(record.get(HEADERS[3]));
                pokemonDto.setTotal(Integer.parseInt(record.get(HEADERS[4])));
                pokemonDto.setHp(Integer.parseInt(record.get(HEADERS[5])));
                pokemonDto.setAttack(Integer.parseInt(record.get(HEADERS[6])));
                pokemonDto.setDefense(Integer.parseInt(record.get(HEADERS[7])));
                pokemonDto.setSoAtk(Integer.parseInt(record.get(HEADERS[8])));
                pokemonDto.setSpDef(Integer.parseInt(record.get(HEADERS[9])));
                pokemonDto.setSpeed(Integer.parseInt(record.get(HEADERS[10])));
                pokemonDto.setGeneration(Integer.parseInt(record.get(HEADERS[11])));
                pokemonDto.setLegendary(Boolean.parseBoolean(record.get(HEADERS[12])));

                listPokemon.add(pokemonDto);
            }
            reader.close();
        } catch (Exception e) {

            throw new PokemonCSVReaderException(e.getMessage());
        }
        return listPokemon;

    }
}
