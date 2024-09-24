package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.DTO.ProjectDTO;
import com.pentalog.KitKat.Entities.Project;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.ProjectRepository;
import com.pentalog.KitKat.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;


    public ProjectService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public List<ProjectDTO> getProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project project : projects) {
            ProjectDTO projectDTO = new ProjectDTO();

            projectDTO.setProjectID(project.getProjectId());

            if(project.getProjectName() != null) {
                projectDTO.setProjectName(project.getProjectName());
            }
            else{
                projectDTO.setProjectName(null);
            }

            if(project.getManager() != null) {
                projectDTO.setManager(userRepository.findById(project.getManager().getUserId()).get().getFirstName() +
                        " " + userRepository.findById(project.getManager().getUserId()).get().getLastName());
            }
            else{
                projectDTO.setManager(null);
            }

            if(project.getStartDate() != null) {
                projectDTO.setStartDate(project.getStartDate());
            }
            else{
                projectDTO.setStartDate(null);
            }

            if(project.getFinishDate() != null) {
                projectDTO.setFinishDate(project.getFinishDate());
            }
            else{
                projectDTO.setFinishDate(null);
            }

            if(project.getStatus() != null) {
                projectDTO.setStatus(project.getStatus());
            }
            else{
                projectDTO.setStatus(null);
            }

            projectDTOs.add(projectDTO);
        }

        return projectDTOs;
    }

    public boolean setProject(Integer workerId, String projectName) {
        User user = userRepository.findById(workerId).get();
        if(user == null){
            return false;
        }
        else{
            if(projectName == null)
            {
                user.setProject(null);
                userRepository.save(user);
                log.info("User with id {} removed from project. ", workerId);
                return true;
            }
            if(projectRepository.findByProjectName(projectName).get() == null) {
                return false;
            }
            else{
                user.setProject(projectRepository.findByProjectName(projectName).get());
                userRepository.save(user);
                log.info("Project set to user with id: {} ", workerId);
                return true;
            }
        }
    }

}
