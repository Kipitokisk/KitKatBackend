package com.pentalog.KitKat.Service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordGeneratorService {
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_+[]/~-";

    private static final String ALL_CHARACTERS = LOWER_CASE + UPPER_CASE + DIGITS + SYMBOLS;
    private static final SecureRandom random = new SecureRandom();

    public String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters.");
        }

        StringBuilder password = new StringBuilder(length);

        // Ensure the password has at least one lower-case letter, one upper-case letter, one number, and one symbol
        password.append(getRandomCharacter(LOWER_CASE));
        password.append(getRandomCharacter(UPPER_CASE));
        password.append(getRandomCharacter(DIGITS));
        password.append(getRandomCharacter(SYMBOLS));

        // Fill the remaining characters with a random selection from all characters
        for (int i = 4; i < length; i++) {
            password.append(getRandomCharacter(ALL_CHARACTERS));
        }

        // Shuffle the password to prevent predictable patterns
        return shufflePassword(password.toString());
    }

    private static char getRandomCharacter(String characterSet) {
        return characterSet.charAt(random.nextInt(characterSet.length()));
    }

    private static String shufflePassword(String password) {
        char[] passwordChars = password.toCharArray();
        for (int i = passwordChars.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap characters at i and j
            char temp = passwordChars[i];
            passwordChars[i] = passwordChars[j];
            passwordChars[j] = temp;
        }
        return new String(passwordChars);
    }
}
