package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.Project;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.ProjectRepository;
import com.pentalog.KitKat.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public boolean setProject(Integer workerId, String projectName) {
        User user = userRepository.findById(workerId).get();
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
