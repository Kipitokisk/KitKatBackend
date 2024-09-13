package com.pentalog.KitKat.Controller;

import com.pentalog.KitKat.DTO.CityDTO;
import com.pentalog.KitKat.Entities.*;
import com.pentalog.KitKat.Service.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public Country saveCountry(@Valid @RequestBody Country country) {
        return this.countryService.saveCountry(country);
    }

    @PostMapping("/save-city")
    public City saveCity(@Valid @RequestBody CityDTO city) {
        return this.cityService.saveCity(city);
    }

    @PostMapping("/save-position")
    public Position savePosition(@Valid @RequestBody Position position) {
        return this.positionService.savePosition(position);
    }

    @PostMapping("/save-role")
    public Role saveRole(@Valid @RequestBody Role role){return this.roleService.saveRole(role);}
    @PostMapping("/add-status")
    public Status addStatus(@Valid @RequestBody Status status){return this.statusService.saveStatus(status);}
}
