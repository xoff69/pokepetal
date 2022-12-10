package com.xoff.pokepetal.util;

import com.xoff.pokepetal.dto.PokemonDto;
import com.xoff.pokepetal.exception.CSVReaderException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PokemonCSVUtil {

    private static String[] HEADERS = {"#", "Name", "Type 1", "Type 2", "Total", "HP", "Attack", "Defense", "Sp. Atk", "Sp. Def", "Speed", "Generation", "Legendary"};

    public static List<PokemonDto> loadListPokemonsFromCSVUrl(String dataUrl) throws CSVReaderException {
        List<PokemonDto> listPokemon = new ArrayList<>();
        try {
            final URL url = new URL(dataUrl);


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(reader);

            for (CSVRecord record : records) {

                PokemonDto pokemonBusiness = new PokemonDto();
                pokemonBusiness.setId(Long.parseLong(record.get(HEADERS[0])));
                pokemonBusiness.setName(record.get(HEADERS[1]));
                pokemonBusiness.setType1(record.get(HEADERS[2]));
                pokemonBusiness.setType2(record.get(HEADERS[3]));
                pokemonBusiness.setTotal(Integer.parseInt(record.get(HEADERS[4])));
                pokemonBusiness.setHp(Integer.parseInt(record.get(HEADERS[5])));
                pokemonBusiness.setAttack(Integer.parseInt(record.get(HEADERS[6])));
                pokemonBusiness.setDefense(Integer.parseInt(record.get(HEADERS[7])));
                pokemonBusiness.setSoAtk(Integer.parseInt(record.get(HEADERS[8])));
                pokemonBusiness.setSpDef(Integer.parseInt(record.get(HEADERS[9])));
                pokemonBusiness.setSpeed(Integer.parseInt(record.get(HEADERS[10])));
                pokemonBusiness.setGeneration(Integer.parseInt(record.get(HEADERS[11])));
                pokemonBusiness.setLegendary(Boolean.parseBoolean(record.get(HEADERS[12])));

                listPokemon.add(pokemonBusiness);
            }
            reader.close();
        } catch (Exception e) {

            throw new CSVReaderException(e.getMessage());
        }
        return listPokemon;

    }
}
