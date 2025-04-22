package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Role;
import com.pentalog.KitKat.Repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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
                log.warn("Role already exists: {}", role.getName());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Role already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(errorResponse);
            }

            // If the role doesn't exist, save the new role
            Role savedRole = this.roleRepository.save(role);
            log.info("Role added: {}", role.getName());
            return ResponseEntity.ok(savedRole);

        } catch (Exception e) {
            log.error("Error while saving status", e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during save role");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    public Optional<Role> findByName(String name) { return roleRepository.findByName(name);
    }
}
