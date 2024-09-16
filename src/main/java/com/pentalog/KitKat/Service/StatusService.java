package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Status;
import com.pentalog.KitKat.Repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public ResponseEntity<?> saveStatus(Status status) {
        try {
            // Check if the status already exists
            Optional<Status> existingStatus = this.statusRepository.findByName(status.getName());
            if (existingStatus.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Status '" + status.getName() + "' already exists.");
            }

            // If status doesn't exist, save the new status
            Status savedStatus = this.statusRepository.save(status);
            return ResponseEntity.ok(savedStatus);

        } catch (Exception e) {
            log.error("Error while saving status", e);

            // Return a bad request response with a meaningful error message
            return ResponseEntity.badRequest().body("Failed to save status: " + e.getMessage());
        }
    }
    public Optional<Status> findByName(String name) {return  statusRepository.findByName(name);}
}
