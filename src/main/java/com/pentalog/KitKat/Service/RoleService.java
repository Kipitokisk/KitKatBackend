package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Role;
import com.pentalog.KitKat.Repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> saveRole(Role role) {
        try {
            // Check if the role already exists
            Optional<Role> existingRole = this.roleRepository.findByName(role.getName());
            if (existingRole.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Role '" + role.getName() + "' already exists.");
            }

            // If the role doesn't exist, save the new role
            Role savedRole = this.roleRepository.save(role);
            return ResponseEntity.ok(savedRole);

        } catch (Exception e) {
            log.error("Error while saving status", e);
            return ResponseEntity.badRequest().body("Failed to save role: " + e.getMessage());
        }
    }

    public Optional<Role> findByName(String name) { return roleRepository.findByName(name);
    }
}
