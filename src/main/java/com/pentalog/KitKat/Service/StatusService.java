package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Status;
import com.pentalog.KitKat.Repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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
                log.warn("Status already exists: {}", status.getName());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Status already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(errorResponse);
            }

            // If status doesn't exist, save the new status
            Status savedStatus = this.statusRepository.save(status);
            log.info("Status added: {}", status.getName());
            return ResponseEntity.ok(savedStatus);

        } catch (Exception e) {
            log.error("Error while saving status", e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during save status");
            errorResponse.put("error", e.getMessage());
            // Return a bad request response with a meaningful error message
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    public Optional<Status> findByName(String name) {return  statusRepository.findByName(name);}
}
