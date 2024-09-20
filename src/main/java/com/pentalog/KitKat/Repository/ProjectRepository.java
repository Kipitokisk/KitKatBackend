package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findByProjectName(String projectName);
}
