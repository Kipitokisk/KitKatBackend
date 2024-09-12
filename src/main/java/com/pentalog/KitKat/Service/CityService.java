package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.City;
import com.pentalog.KitKat.Repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }
}
