package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.Seniority;
import com.pentalog.KitKat.Entities.SkillRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRatingRepository extends JpaRepository<SkillRating, Integer> {
    List<SkillRating> findAllByUser_UserId(Integer userId);
}