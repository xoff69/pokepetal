package com.xoff.pokepetal;

import com.xoff.pokepetal.business.PokemonMapper;
import com.xoff.pokepetal.exception.CSVReaderException;
import com.xoff.pokepetal.service.PokePetalService;
import com.xoff.pokepetal.util.CSVUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class PokepetalApplication {

    @Value("${application.seed.dataurl}")
    private String dataUrl;

    @Autowired
    private PokePetalService pokePetalService;


    public static void main(String[] args) {
        SpringApplication.run(PokepetalApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeDatabaseAfterStartup() {
        log.info("Importation des donnees du fichier csv a l'url:" + dataUrl);
        try {

            pokePetalService.saveAll(PokemonMapper.INSTANCE.map(CSVUtil.loadPokemons(dataUrl)));
            log.info("Importation reussie");
        } catch (CSVReaderException e) {
            log.error("Erreur de recuperation des donnees du fichier CSV, le traitement continue, erreur non bloquante");
        }
    }
}
