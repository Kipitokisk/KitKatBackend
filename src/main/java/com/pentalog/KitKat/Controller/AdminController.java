package com.pentalog.KitKat.Controller;

import com.pentalog.KitKat.DTO.CityDTO;
import com.pentalog.KitKat.Entities.City;
import com.pentalog.KitKat.Entities.Country;
import com.pentalog.KitKat.Entities.Position;
import com.pentalog.KitKat.Entities.Role;
import com.pentalog.KitKat.Service.CityService;
import com.pentalog.KitKat.Service.CountryService;
import com.pentalog.KitKat.Service.PositionService;
import com.pentalog.KitKat.Service.RoleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final CountryService countryService;
    private final CityService cityService;
    private final PositionService positionService;
    private final RoleService roleService;

    public AdminController(CountryService countryService, CityService cityService, PositionService positionService, RoleService roleService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.positionService = positionService;
        this.roleService = roleService;
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
}
