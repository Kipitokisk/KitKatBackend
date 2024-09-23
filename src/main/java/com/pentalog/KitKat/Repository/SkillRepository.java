package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.Seniority;
import com.pentalog.KitKat.Entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
