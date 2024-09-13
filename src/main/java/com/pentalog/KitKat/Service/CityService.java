package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.DTO.CityDTO;
import com.pentalog.KitKat.Entities.City;
import com.pentalog.KitKat.Entities.Country;
import com.pentalog.KitKat.Repository.CityRepository;
import com.pentalog.KitKat.Repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public City saveCity(CityDTO cityDTO) {
        Country country = countryRepository.findByCountryName(cityDTO.getCountryName());

        City city = new City();
        city.setCityName(cityDTO.getCityName());
        city.setCountry(country);

        return cityRepository.save(city);
    }
}
