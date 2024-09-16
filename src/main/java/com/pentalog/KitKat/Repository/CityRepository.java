package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findByCountryCountryId(Integer countryId);
    Optional<City> findByCityName(String name);
}
