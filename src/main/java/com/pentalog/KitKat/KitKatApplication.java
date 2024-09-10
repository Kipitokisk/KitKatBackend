package com.pentalog.KitKat;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KitKatApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		String hashKey = dotenv.get("HASH_KEY");
		SpringApplication.run(KitKatApplication.class, args);
	}

}
