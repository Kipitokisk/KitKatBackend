package com.pentalog.KitKat.Controller.ManagerController;

import com.pentalog.KitKat.DTO.WorkerProjectDTO;
import com.pentalog.KitKat.DTO.WorkerToManagerDashboardDTO;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Service.ProjectService;
import com.pentalog.KitKat.Service.UserService;
import com.pentalog.KitKat.Service.WorkersToManagerDashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manager")
public class ManagerController {

    private final WorkersToManagerDashboardService workersToManagerDashboardService;
    private final ProjectService projectService;

    public ManagerController(WorkersToManagerDashboardService workersToManagerDashboardService, UserService userService, ProjectService projectService) {
        this.workersToManagerDashboardService = workersToManagerDashboardService;
        this.projectService = projectService;
    }

    @GetMapping("/worker")
    public ResponseEntity<?> employeesDashboard() {
        log.info("Request to get all workers");
        return ResponseEntity.ok(workersToManagerDashboardService.returnWorkersToManagerDashboard());
    }

    @GetMapping("/worker/{id}")
    public ResponseEntity<?> getWorkerByEmail(@PathVariable Integer id) {
        log.info("Received request to find user by id: {}", id);
        return ResponseEntity.ok(workersToManagerDashboardService.returnWorkerById(id));
    }

    @GetMapping("/worker/project")
    public ResponseEntity<?> getProjects() {
        log.info("Request to get all projects");
        return ResponseEntity.ok(projectService.getProjects());
    }

    @PostMapping("/worker/project")
    public ResponseEntity<?> setProject(@RequestBody WorkerProjectDTO body) {
        log.info("Received request to set project: {}", body);
        return  ResponseEntity.ok(projectService.setProject(body.getWorkerId(), body.getProjectName()));
    }
}
