package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Role;
import com.pentalog.KitKat.Repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
