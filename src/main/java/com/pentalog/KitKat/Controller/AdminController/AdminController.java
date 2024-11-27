package com.pentalog.KitKat.Controller.AdminController;

import com.pentalog.KitKat.DTO.CityDTO;
import com.pentalog.KitKat.DTO.SkillDTO;
import com.pentalog.KitKat.Entities.*;
import com.pentalog.KitKat.Service.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private final CountryService countryService;
    private final CityService cityService;
    private final PositionService positionService;
    private final RoleService roleService;
    private final StatusService statusService;
    private final LanguageService languageService;
    private final SkillTypesService skillTypesService;
    private final SkillService skillService;

    public AdminController(CountryService countryService, CityService cityService, PositionService positionService, RoleService roleService, StatusService statusService, LanguageService languageService, SkillTypesService skillTypesService, SkillService skillService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.positionService = positionService;
        this.roleService = roleService;
        this.statusService = statusService;
        this.languageService = languageService;
        this.skillTypesService = skillTypesService;
        this.skillService = skillService;
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

    @PostMapping("/save-skill-type")
    public ResponseEntity<?> saveSkillType(@Valid @RequestBody SkillType skillType) {
        return skillTypesService.saveSkillType(skillType);
    }

    @PostMapping("/save-skill")
    public ResponseEntity<?> saveSkill(@Valid @RequestBody SkillDTO skillDTO) {
        return skillService.saveSkill(skillDTO);
    }

    @PostMapping("/save-language")
    public ResponseEntity<?> saveLanguage(@RequestBody Language language) {
        return languageService.saveLanguage(language);
    }
}
