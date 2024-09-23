package com.pentalog.KitKat.Repository;


import com.pentalog.KitKat.Entities.Role;
import com.pentalog.KitKat.Entities.Seniority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeniorityRepository extends JpaRepository<Seniority, Integer> {

}
