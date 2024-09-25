package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.Seniority;
import com.pentalog.KitKat.Entities.Skill;
import com.pentalog.KitKat.Entities.SkillRating;
import com.pentalog.KitKat.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRatingRepository extends JpaRepository<SkillRating, Integer> {
    List<SkillRating> findAllByUser_UserId(Integer userId);
    Optional<SkillRating> findByUserAndSkill(User user, Skill skill);
}

