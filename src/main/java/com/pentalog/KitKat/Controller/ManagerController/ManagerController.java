package com.pentalog.KitKat.Controller.ManagerController;

import com.pentalog.KitKat.DTO.WorkerProjectDTO;
import com.pentalog.KitKat.DTO.WorkerToManagerDashboardDTO;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Service.ProjectService;
import com.pentalog.KitKat.Service.UserService;
import com.pentalog.KitKat.Service.WorkersToManagerDashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/manager")
public class ManagerController {

    private final WorkersToManagerDashboardService workersToManagerDashboardService;
    private final ProjectService projectService;
    private final UserService userService;

    public ManagerController(WorkersToManagerDashboardService workersToManagerDashboardService, UserService userService, ProjectService projectService) {
        this.workersToManagerDashboardService = workersToManagerDashboardService;
        this.projectService = projectService;
        this.userService = userService;
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

        try {
            boolean isProjectSet = projectService.setProject(body.getWorkerId(), body.getProjectId());
            if (isProjectSet) {
                return ResponseEntity.ok("Project set successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to set project.");
            }
        } catch (Exception e) {
            log.error("Error occurred while setting project: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while setting the project.");
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<User>> searchUsers(
            @RequestParam(required = false) List<String> position,
            @RequestParam(required = false) List<String> seniority,
            @RequestParam(required = false) List<String> country,
            @RequestParam(required = false) List<String> skill,
            @RequestParam(required = false) List<String> languages,
            @RequestParam(required = false) List<String> roles,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> filteredUsers = userService.searchUsers(position, seniority, country, skill, languages, roles, pageable);

        return ResponseEntity.ok(filteredUsers);
    }

    @GetMapping("/without-project")
    public ResponseEntity<Map<String, Integer>> countUsersWithoutProject() {
        Integer count = userService.countUsersWithoutProject();
        Map<String, Integer> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/without-project/{countryName}")
    public ResponseEntity<Map<String, Integer>> getUserCountWithoutProjectByCountry(@PathVariable String countryName) {
        Integer count = userService.getUserCountWithoutProjectByCountry(countryName);
        Map<String, Integer> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }
}
