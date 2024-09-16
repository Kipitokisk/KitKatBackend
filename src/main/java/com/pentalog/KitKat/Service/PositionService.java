package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Position;
import com.pentalog.KitKat.Repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PositionService {
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public ResponseEntity<?> savePosition(Position position) {
        try {
            // Check if the position already exists
            Optional<Position> existingPosition = this.positionRepository.findByName(position.getName());
            if (existingPosition.isPresent()) {
                log.warn("Position already exists: {}", position.getName());
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Position '" + position.getName() + "' already exists.");
            }

            // If the position doesn't exist, save the new position
            Position savedPosition = this.positionRepository.save(position);
            log.info("Position added: {}", position.getName());
            return ResponseEntity.ok(savedPosition);

        } catch (Exception e) {
            log.error("Error while saving status", e);
            return ResponseEntity.badRequest().body("Failed to save position: " + e.getMessage());
        }
    }
    public Optional<Position> findByName(String name) {return positionRepository.findByName(name);}
}
