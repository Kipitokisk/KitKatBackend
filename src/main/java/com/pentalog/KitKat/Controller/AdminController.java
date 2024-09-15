package com.pentalog.KitKat.Controller;

import com.pentalog.KitKat.DTO.CityDTO;
import com.pentalog.KitKat.Entities.*;
import com.pentalog.KitKat.Service.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private final CountryService countryService;
    private final CityService cityService;
    private final PositionService positionService;
    private final RoleService roleService;
    private final StatusService statusService;

    public AdminController(CountryService countryService, CityService cityService, PositionService positionService, RoleService roleService, StatusService statusService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.positionService = positionService;
        this.roleService = roleService;
        this.statusService = statusService;
    }

    @PostMapping("/save-country")
    public ResponseEntity<?> saveCountry(@Valid @RequestBody Country country) {
        try {
            // Check if the country already exists
            Optional<Country> existingCountry = Optional.ofNullable(this.countryService.findByName(country.getCountryName()));
            if (existingCountry.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Country '" + country.getCountryName() + "' already exists.");
            }

            // If the country doesn't exist, save the new country
            Country savedCountry = this.countryService.saveCountry(country);
            return ResponseEntity.ok(savedCountry);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save country: " + e.getMessage());
        }
    }

    @PostMapping("/save-city")
    public ResponseEntity<?> saveCity(@Valid @RequestBody CityDTO cityDTO) {
        try {
            // Check if the country exists
            Optional<Country> country = Optional.ofNullable(countryService.findByName(cityDTO.getCountryName()));
            if (!country.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Country with ID '" + countryService.findByName(cityDTO.getCountryName())+ "' does not exist.");
            }

            // Check if the city already exists
            Optional<City> existingCity = cityService.findByName(cityDTO.getCityName());
            if (existingCity.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("City '" + cityDTO.getCityName() + "' already exists.");
            }

            // If both the country exists and the city does not exist, save the new city
            City savedCity = cityService.saveCity(cityDTO);
            return ResponseEntity.ok(savedCity);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save city: " + e.getMessage());
        }
    }


    @PostMapping("/save-position")
    public ResponseEntity<?> savePosition(@Valid @RequestBody Position position) {
        try {
            // Check if the position already exists
            Optional<Position> existingPosition = this.positionService.findByName(position.getName());
            if (existingPosition.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Position '" + position.getName() + "' already exists.");
            }

            // If the position doesn't exist, save the new position
            Position savedPosition = this.positionService.savePosition(position);
            return ResponseEntity.ok(savedPosition);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save position: " + e.getMessage());
        }
    }

    @PostMapping("/save-role")
    public ResponseEntity<?> saveRole(@Valid @RequestBody Role role) {
        try {
            // Check if the role already exists
            Optional<Role> existingRole = this.roleService.findByName(role.getName());
            if (existingRole.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Role '" + role.getName() + "' already exists.");
            }

            // If the role doesn't exist, save the new role
            Role savedRole = this.roleService.saveRole(role);
            return ResponseEntity.ok(savedRole);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save role: " + e.getMessage());
        }
    }
    @PostMapping("/add-status")
    public ResponseEntity<?> addStatus(@Valid @RequestBody Status status) {
        try {
            // Check if the status already exists
            Optional<Status> existingStatus = this.statusService.findByName(status.getName());
            if (existingStatus.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Status '" + status.getName() + "' already exists.");
            }

            // If status doesn't exist, save the new status
            Status savedStatus = this.statusService.saveStatus(status);
            return ResponseEntity.ok(savedStatus);

        } catch (Exception e) {
            log.error("Error while saving status", e);

            // Return a bad request response with a meaningful error message
            return ResponseEntity.badRequest().body("Failed to save status: " + e.getMessage());
        }
    }
}
