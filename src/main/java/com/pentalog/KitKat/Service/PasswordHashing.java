package com.pentalog.KitKat.Service;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class PasswordHashing {

    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int HASH_ITERATIONS = 1000000;
    private static final int HASH_LENGTH = 256;

    // Retrieves the password hash using the password and salt from environment
    public byte[] getPasswordHash(String password) throws Exception {
        byte[] passwordSalt = getEnvPasswordSalt();

        // Derive the password hash using PBKDF2WithHmacSHA256
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), passwordSalt, HASH_ITERATIONS, HASH_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
        return factory.generateSecret(spec).getEncoded();
    }

    // Retrieves the password salt from environment variables
    private byte[] getEnvPasswordSalt() {
        String saltString = System.getenv("HASH_KEY");
        if (saltString == null) {
            throw new IllegalArgumentException("Environment variable HASH_SALT not set");
        }
        return Base64.getDecoder().decode(saltString);
    }
}
