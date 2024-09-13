package com.pentalog.KitKat.Service;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class PasswordHashing {
    public byte[] getPasswordHash(String password, byte[] passwordSalt) throws Exception {
        // Retrieve password salt key from environment variable
        String passwordKey = System.getenv("HASH_KEY");
        if (passwordKey == null) {
            throw new IllegalArgumentException("Environment variable PASSWORD_KEY not set");
        }

        // Combine password key from env with salt
        String passwordSaltPlusString = passwordKey + Base64.getEncoder().encodeToString(passwordSalt);

        // Derive the password hash using PBKDF2WithHmacSHA256
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), passwordSaltPlusString.getBytes(StandardCharsets.US_ASCII), 1000000, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        return factory.generateSecret(spec).getEncoded();
    }

    // Helper method to generate a new random salt
    public static byte[] generateSalt() throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}