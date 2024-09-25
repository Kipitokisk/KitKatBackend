package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.DTO.SkillDTO;
import com.pentalog.KitKat.Entities.City;
import com.pentalog.KitKat.Entities.Skill;
import com.pentalog.KitKat.Entities.SkillType;
import com.pentalog.KitKat.Repository.SkillRepository;
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
public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillTypesRepository skillTypesRepository;

    public SkillService(SkillRepository skillRepository, SkillTypesRepository skillTypesRepository) {
        this.skillRepository = skillRepository;

        this.skillTypesRepository = skillTypesRepository;
    }

    public ResponseEntity<?> saveSkill(SkillDTO skillDTO) {
        log.debug("Saving skill: {}", skillDTO.getSkillName());
        try {
            // Check if the skill type exists
            Optional<SkillType> skillType = Optional.ofNullable(skillTypesRepository.findByName(skillDTO.getSkillType()));
            if (!skillType.isPresent()) {
                log.warn("Skill type does not exist: {}", skillDTO.getSkillType());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Skill type does not exist");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(errorResponse);
            }

            // Check if the skill already exists
            Optional<Skill> existingSkill = skillRepository.findByName(skillDTO.getSkillName());
            if (existingSkill.isPresent()) {
                log.warn("Skill already exists: {}", skillDTO.getSkillName());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Skill already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Skill '" + skillDTO.getSkillName() + "' already exists.");
            }

            // If both the country exists and the city does not exist, save the new city
            Skill savedSkill = new Skill();
            savedSkill.setName(skillDTO.getSkillName());
            savedSkill.setSkillType(skillTypesRepository.findByName(skillDTO.getSkillType()));
            this.skillRepository.save(savedSkill);
            log.info("Skill added: {}", skillDTO.getSkillName());
            return ResponseEntity.ok(savedSkill);

        } catch (Exception e) {
            log.error("Save skill failed: {}", e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during save skill");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
