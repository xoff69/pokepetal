package com.xoff.pokepetal.util;

import com.xoff.pokepetal.business.PokemonBusiness;
import com.xoff.pokepetal.exception.CSVReaderException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    private static String[] HEADERS = {"#", "Name", "Type 1", "Type 2", "Total", "HP",
            "Attack", "Defense", "Sp. Atk", "Sp. Def", "Speed", "Generation", "Legendary"};

    public static List<PokemonBusiness> loadPokemons(String dataUrl) throws CSVReaderException {
        List<PokemonBusiness> listPokemon = new ArrayList<>();
        try {
            final URL url = new URL(dataUrl);


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .parse(reader);
            System.out.println("loadPokemons" + records);
            for (CSVRecord record : records) {
                String id = record.get(HEADERS[0]);
                String name = record.get(HEADERS[1]);
                System.out.println(" " + id + " " + name);

                PokemonBusiness pokemonBusiness = new PokemonBusiness();
                pokemonBusiness.setId(Long.parseLong(id));
                pokemonBusiness.setName(name
                );
                listPokemon.add(pokemonBusiness);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CSVReaderException(e.getMessage());
        }
        return listPokemon;

    }
}
