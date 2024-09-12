package com.pentalog.KitKat.Controller;

import com.pentalog.KitKat.Entities.City;
import com.pentalog.KitKat.Entities.Country;
import com.pentalog.KitKat.Service.CityService;
import com.pentalog.KitKat.Service.CountryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final CountryService countryService;
    private final CityService cityService;

    public AdminController(CountryService countryService, CityService cityService) {
        this.countryService = countryService;
        this.cityService = cityService;
    }

    @PostMapping("/save-country")
    public Country saveCountry(@RequestBody Country country) {
        return this.countryService.saveCountry(country);
    }

    @PostMapping("/save-city")
    public City saveCity(@RequestBody City city) {
        return this.cityService.saveCity(city);
    }
}
