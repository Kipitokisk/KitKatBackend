package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.Seniority;
import com.pentalog.KitKat.Entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
    Optional<Skill> findByName(String name);
}
