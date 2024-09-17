package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Country;
import com.pentalog.KitKat.Repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public ResponseEntity<?> saveCountry(Country country) {
        try {
            // Check if the country already exists
            Optional<Country> existingCountry = Optional.ofNullable(this.countryRepository.findByCountryName(country.getCountryName()));
            if (existingCountry.isPresent()) {
                log.warn("Country already exists: {}", country.getCountryName());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Country already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(errorResponse);
            }

            // If the country doesn't exist, save the new country
            Country savedCountry = this.countryRepository.save(country);
            log.info("Country added: {}", country.getCountryName());
            return ResponseEntity.ok(savedCountry);

        } catch (Exception e) {
            log.error("Error while saving status", e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during save country");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    public Country findByName(String name) {return countryRepository.findByCountryName(name);}
}
