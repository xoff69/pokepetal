package com.xoff.pokepetal.util;

import com.xoff.pokepetal.business.PokemonBusiness;
import com.xoff.pokepetal.exception.CSVReaderException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static List<PokemonBusiness> loadPokemons(String dataUrl) throws CSVReaderException {
        List<PokemonBusiness> listPokemonBusiness = new ArrayList<>();
        BufferedReader buffer = null;
        String line = "";
        String csvSplitBy = ",";

        try {
            URL url = new URL(dataUrl);
            URLConnection connection = url.openConnection();

            InputStreamReader input = new InputStreamReader(connection.getInputStream());

            buffer = new BufferedReader(input);
            while ((line = buffer.readLine()) != null) {
                String[] room = line.split(csvSplitBy);
                System.out.println(line);
                // System.out.println("room [capacity =" + room[0] + " , price=" + room[1]);
            }
        } catch (MalformedURLException e) {
            throw new CSVReaderException("CSVUtil: malformation URL " + dataUrl + " " + e.getMessage());

        } catch (FileNotFoundException e) {
            throw new CSVReaderException("CSVUtil: fichier introuvable a " + dataUrl + " " + e.getMessage());
        } catch (IOException e) {
            throw new CSVReaderException("CSVUtil: IOException lecture a " + dataUrl + " " + e.getMessage());
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    throw new CSVReaderException("CSVUtil: IOException fermeture a " + dataUrl + " " + e.getMessage());
                }
            }
        }

        return listPokemonBusiness;

    }
}
