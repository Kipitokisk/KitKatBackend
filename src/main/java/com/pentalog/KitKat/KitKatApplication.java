package com.pentalog.KitKat;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KitKatApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
				.directory("/opt/demo")  // Set the directory where .env is located
				.load();
		String hashKey = dotenv.get("HASH_KEY");
		SpringApplication.run(KitKatApplication.class, args);
	}

}
