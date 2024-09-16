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
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Country with ID '" + countryRepository.findByCountryName(cityDTO.getCountryName())+ "' does not exist.");
            }

            // Check if the city already exists
            Optional<City> existingCity = cityRepository.findByCityName(cityDTO.getCityName());
            if (existingCity.isPresent()) {
                log.warn("City already exists: {}", cityDTO.getCityName());
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
            log.error("Error while saving status", e);
            return ResponseEntity.badRequest().body("Failed to save city: " + e.getMessage());
        }
    }

    public Optional<City> findByName(String name) {return cityRepository.findByCityName(name);}
}
