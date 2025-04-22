package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Role;
import com.pentalog.KitKat.Entities.SkillType;
import com.pentalog.KitKat.Repository.SkillTypesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class SkillTypesService {
    private final SkillTypesRepository skillTypesRepository;

    public SkillTypesService(SkillTypesRepository skillTypesRepository) {
        this.skillTypesRepository = skillTypesRepository;
    }

    public ResponseEntity<?> saveSkillType(SkillType skillType) {
        try {
            // Check if the skill type already exists
            Optional<SkillType> existingSkillType = Optional.ofNullable(this.skillTypesRepository.findByName(skillType.getName()));
            if (existingSkillType.isPresent()) {
                log.warn("Skill type already exists: {}", skillType.getName());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Role already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(errorResponse);
            }

            // If the role doesn't exist, save the new role
            SkillType savedSkillType = this.skillTypesRepository.save(skillType);
            log.info("Skill type added: {}", skillType.getName());
            return ResponseEntity.ok(savedSkillType);

        } catch (Exception e) {
            log.error("Error while saving status", e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during save skill type");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
