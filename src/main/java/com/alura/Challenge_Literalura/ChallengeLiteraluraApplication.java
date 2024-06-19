package com.alura.Challenge_Literalura;

import com.alura.Challenge_Literalura.Principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutoresRepository autoresrepository;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal( libroRepository, autoresrepository);
		principal.muestraElMenu();
	}
}
