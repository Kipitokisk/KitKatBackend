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
        return countryService.saveCountry(country);
    }

    @PostMapping("/save-city")
    public ResponseEntity<?> saveCity(@Valid @RequestBody CityDTO cityDTO) {
        return cityService.saveCity(cityDTO);
    }


    @PostMapping("/save-position")
    public ResponseEntity<?> savePosition(@Valid @RequestBody Position position) {
        return positionService.savePosition(position);
    }

    @PostMapping("/save-role")
    public ResponseEntity<?> saveRole(@Valid @RequestBody Role role) {
        return roleService.saveRole(role);
    }
    @PostMapping("/add-status")
    public ResponseEntity<?> addStatus(@Valid @RequestBody Status status) {
        return statusService.saveStatus(status);
    }
}
