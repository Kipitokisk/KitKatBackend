package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.DTO.CreateProjectDTO;
import com.pentalog.KitKat.DTO.ProjectDTO;
import com.pentalog.KitKat.Entities.Language;
import com.pentalog.KitKat.Entities.Project;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.ProjectRepository;
import com.pentalog.KitKat.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;


    public ProjectService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public ResponseEntity<?> saveProject(CreateProjectDTO createProjectDTO) {
        try {
            Optional<Project> existingProject = this.projectRepository.findByProjectName(createProjectDTO.getProjectName());
            if (existingProject.isPresent()) {
                log.warn("Project already exists: {}", createProjectDTO.getProjectName());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Project already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(errorResponse);
            }
            Project project = new Project();
            project.setProjectName(createProjectDTO.getProjectName());
            project.setFinishDate(createProjectDTO.getFinishDate());
            project.setStartDate(LocalDateTime.now());
            project.setManager(userRepository.findById(createProjectDTO.getManagerId()).orElse(null));
            project.setStatus(true);
            project.setDescription(createProjectDTO.getDescription());
            projectRepository.save(project);
            log.info("Project added: {}", project.getProjectName());
            return ResponseEntity.ok(project);

        } catch (Exception e) {
            log.error("Error while saving status", e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during save project");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    public List<Project> getProjects() {
        return this.projectRepository.findAll();
    }

//    public List<ProjectDTO> getProjects() {
//        List<Project> projects = projectRepository.findAll();
//        List<ProjectDTO> projectDTOs = new ArrayList<>();
//        for (Project project : projects) {
//            ProjectDTO projectDTO = new ProjectDTO();
//
//            projectDTO.setProjectID(project.getProjectId());
//
//            if(project.getProjectName() != null) {
//                projectDTO.setProjectName(project.getProjectName());
//            }
//            else{
//                projectDTO.setProjectName(null);
//            }
//
//            if(project.getManager() != null) {
//                projectDTO.setManager(userRepository.findById(project.getManager().getUserId()).get().getFirstName() +
//                        " " + userRepository.findById(project.getManager().getUserId()).get().getLastName());
//            }
//            else{
//                projectDTO.setManager(null);
//            }
//
//            if(project.getStartDate() != null) {
//                projectDTO.setStartDate(project.getStartDate());
//            }
//            else{
//                projectDTO.setStartDate(null);
//            }
//
//            if(project.getFinishDate() != null) {
//                projectDTO.setFinishDate(project.getFinishDate());
//            }
//            else{
//                projectDTO.setFinishDate(null);
//            }
//
//            if(project.getStatus() != null) {
//                projectDTO.setStatus(project.getStatus());
//            }
//            else{
//                projectDTO.setStatus(null);
//            }
//
//            if (project.getDescription() != null) {
//                projectDTO.setDescription(project.getDescription());
//            }
//            else {
//                projectDTO.setDescription(null);
//            }
//
//            projectDTOs.add(projectDTO);
//        }
//
//        return projectDTOs;
//    }

    public ResponseEntity<?> setProject(Integer userId, Integer projectId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (projectId == 0) {
            Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
            project.getWorkers().remove(user);
            projectRepository.save(project);
            user.setProject(null);
            userRepository.save(user);
            log.info("User with id {} removed from project with id {}.", userId, projectId);
            return ResponseEntity.ok(user);
        } else {
            Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
            project.getWorkers().add(user);
            projectRepository.save(project);
            user.setProject(project);
            userRepository.save(user);
            log.info("User with id {} added to project with id {}.", userId, projectId);
            return ResponseEntity.ok(user);
        }
    }

}
