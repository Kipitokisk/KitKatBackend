package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.DTO.CityDTO;
import com.pentalog.KitKat.Entities.City;
import com.pentalog.KitKat.Entities.Country;
import com.pentalog.KitKat.Repository.CityRepository;
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
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public ResponseEntity<?> saveCity(CityDTO cityDTO) {
        log.debug("Saving city: {}", cityDTO.getCityName());
        try {
            // Check if the country exists
            Optional<Country> country = Optional.ofNullable(countryRepository.findByCountryName(cityDTO.getCountryName()));
            if (!country.isPresent()) {
                log.warn("Country does not exist: {}", cityDTO.getCountryName());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Country does not exist");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(errorResponse);
            }

            // Check if the city already exists
            Optional<City> existingCity = cityRepository.findByCityName(cityDTO.getCityName());
            if (existingCity.isPresent()) {
                log.warn("City already exists: {}", cityDTO.getCityName());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "City already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("City '" + cityDTO.getCityName() + "' already exists.");
            }

            // If both the country exists and the city does not exist, save the new city
            City savedCity = new City();
            savedCity.setCityName(cityDTO.getCityName());
            savedCity.setCountry(countryRepository.findByCountryName(cityDTO.getCountryName()));
            this.cityRepository.save(savedCity);
            log.info("City added: {}", cityDTO.getCityName());
            return ResponseEntity.ok(savedCity);

        } catch (Exception e) {
            log.error("Save city failed: {}", e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during save city");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    public Optional<City> findByName(String name) {return cityRepository.findByCityName(name);}
}
