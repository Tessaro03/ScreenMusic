package ScreenMusic.Screen;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ScreenMusic.Screen.principal.Principal;
import ScreenMusic.Screen.repository.ArtistaRepository;

@SpringBootApplication
public class MusicApplication implements CommandLineRunner{

	@Autowired
	private ArtistaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.menuPrincipal();

	}

}
