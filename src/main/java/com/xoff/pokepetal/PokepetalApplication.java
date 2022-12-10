package com.xoff.pokepetal;

import com.xoff.pokepetal.model.Pokemon;
import com.xoff.pokepetal.service.PokePetalService;
import lombok.AllArgsConstructor;
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
		log.info("hello world, I have just started up"+dataUrl);

		Pokemon p=new Pokemon();
		p.setContent("go");
		pokePetalService.save(p);
	}
}
