package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Language;
import com.pentalog.KitKat.Entities.Position;
import com.pentalog.KitKat.Repository.LanguageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public ResponseEntity<?> saveLanguage(Language language) {
        try {
            // Check if the position already exists
            Optional<Language> existingLanguage = this.languageRepository.findByLanguageName(language.getLanguageName());
            if (existingLanguage.isPresent()) {
                log.warn("Language already exists: {}", language.getLanguageName());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Language already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(errorResponse);
            }

            // If the position doesn't exist, save the new position
            Language savedLanguage = this.languageRepository.save(language);
            log.info("Language added: {}", language.getLanguageName());
            return ResponseEntity.ok(savedLanguage);

        } catch (Exception e) {
            log.error("Error while saving status", e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during save language");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
