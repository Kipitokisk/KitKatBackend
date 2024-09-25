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
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

            // Check if skill exists
            Skill skill = skillRepository.findById(skillId)
                    .orElseThrow(() -> new IllegalArgumentException("Skill not found: " + skillId));

            // Check if skill rating exists for this user and skill
            Optional<SkillRating> existingRating = skillRatingRepository.findByUserAndSkill(user, skill);
            SkillRating skillRating;

            if (existingRating.isPresent()) {
                // Log that the rating already exists and do not update it
                log.warn("Skill rating already exists for user: {} and skill: {}", userId, skillId);
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Skill rating already exists for this user and skill. Use 'submitRating' to update.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            } else {
                // Create a new SkillRating
                skillRating = new SkillRating();
                skillRating.setUser(user);  // Set the User object directly
                skillRating.setSkill(skill);  // Set the Skill object directly
                skillRating.setRatingSum(rating);  // Set the rating sum as the initial rating
                skillRating.setNrOfReviews(1);  // Set the number of reviews to 1
            }

            skillRatingRepository.save(skillRating);
            user.setSkillRating(skillRating);
            userRepository.save(user);
            log.info("Skill rating saved for user: {} and skill: {}", userId, skillId);
            return ResponseEntity.ok(skillRating);

        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            log.error("Failed to save skill rating: {}", e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during save skill rating");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    public ResponseEntity<?> submitRating(Integer userId, Integer skillId, Integer newRating) {
        log.debug("Submitting new rating for user: {}, skill: {}, rating: {}", userId, skillId, newRating);

        try {
            // Fetch the user
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

            // Fetch the skill
            Skill skill = skillRepository.findById(skillId)
                    .orElseThrow(() -> new IllegalArgumentException("Skill not found: " + skillId));

            // Fetch the existing skill rating for the user and skill
            Optional<SkillRating> skillRatingOpt = skillRatingRepository.findByUserAndSkill(user, skill);

            if (!skillRatingOpt.isPresent()) {
                log.warn("Skill rating not found for user: {}, skill: {}", userId, skillId);
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Skill rating not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            // Update the rating sum and number of reviews
            SkillRating skillRating = skillRatingOpt.get();
            skillRating.setRatingSum(newRating);  // Replace this if you want to adjust how you handle new ratings
            // Optionally increment the number of reviews
            skillRating.setNrOfReviews(skillRating.getNrOfReviews() + 1); // Update number of reviews if needed

            skillRatingRepository.save(skillRating);
            user.setSkillRating(skillRating);
            userRepository.save(user);
            log.info("Updated skill rating: {}", skillRating);
            return ResponseEntity.ok(skillRating);

        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            log.error("Error occurred while submitting rating: {}", e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred while submitting the rating");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
