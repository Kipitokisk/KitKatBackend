package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findAllByCountryCountryId(Integer countryId);
}
