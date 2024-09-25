package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.SkillType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillTypesRepository extends JpaRepository<SkillType, Integer> {
    SkillType findByName(String name);
}
