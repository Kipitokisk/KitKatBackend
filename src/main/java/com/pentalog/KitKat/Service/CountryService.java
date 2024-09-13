package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Country;
import com.pentalog.KitKat.Repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }
}
