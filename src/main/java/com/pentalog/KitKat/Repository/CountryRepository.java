package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    List<Country> findAllByCountryName(String name);
}
