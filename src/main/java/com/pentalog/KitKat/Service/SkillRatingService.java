package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Skill;
import com.pentalog.KitKat.Entities.SkillRating;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.SkillRatingRepository;
import com.pentalog.KitKat.Repository.SkillRepository;
import com.pentalog.KitKat.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class SkillRatingService {

    private final SkillRatingRepository skillRatingRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    public SkillRatingService(SkillRatingRepository skillRatingRepository, UserRepository userRepository, SkillRepository skillRepository) {
        this.skillRatingRepository = skillRatingRepository;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
    }

    public ResponseEntity<?> saveSkillRating(Integer userId, Integer skillId, Integer rating) {
        log.debug("Saving skill rating for user: {} and skill: {}", userId, skillId);
        try {
            // Check if user exists
            Optional<User> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                log.warn("User not found: {}", userId);
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            // Check if skill exists
            Optional<Skill> skill = skillRepository.findById(skillId);
            if (!skill.isPresent()) {
                log.warn("Skill not found: {}", skillId);
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Skill not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            // Check if skill rating exists for this user and skill
            Optional<SkillRating> existingRating = skillRatingRepository.findByUserAndSkill(userId, skillId);
            SkillRating skillRating;
            if (existingRating.isPresent()) {
                skillRating = existingRating.get();
                skillRating.setRatingSum(skillRating.getRatingSum() + rating);
                skillRating.setNrOfReviews(skillRating.getNrOfReviews() + 1);
            } else {
                skillRating = new SkillRating();
                skillRating.setUser(user.get());
                skillRating.setSkill(skill.get());
                skillRating.setRatingSum(rating);
                skillRating.setNrOfReviews(1);
            }

            skillRatingRepository.save(skillRating);
            log.info("Skill rating saved for user: {} and skill: {}", userId, skillId);
            return ResponseEntity.ok(skillRating);

        } catch (Exception e) {
            log.error("Failed to save skill rating: {}", e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during save skill rating");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity<?> submitRating(Integer userId, Integer skillId, Integer newRating) {
        Optional<SkillRating> skillRatingOpt = skillRatingRepository.findByUserAndSkill(userId, skillId);

        if (!skillRatingOpt.isPresent()) {
            log.warn("Skill rating not found for user: {}, skill: {}", userId, skillId);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Skill rating not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        SkillRating skillRating = skillRatingOpt.get();
        skillRating.updateRating(newRating);
        skillRatingRepository.save(skillRating);

        log.info("Updated skill rating: {}", skillRating);
        return ResponseEntity.ok(skillRating);
    }
}
