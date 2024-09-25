package com.pentalog.KitKat.Entities.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    // Load the .env file to get the secret key
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("/opt/demo")  // Set the directory where .env is located
            .load();

    // Load the HASH_KEY from the .env file
    private static final String SECRET_KEY = dotenv.get("HASH_KEY");

    // Token validity (e.g., 3 days in milliseconds)
    private static final long EXPIRATION_TIME = 3 * 24 * 60 * 60 * 1000;

    // Method to generate a JWT token
    public static String generateToken(String userId, String email) {
        return Jwts.builder()
                .setSubject(userId) // Set user ID or email as subject
                .claim("email", email)
                .setIssuedAt(new Date()) // Set issue date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration date
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Sign with secret key from .env
                .compact(); // Build and return the token
    }
}

