package com.pentalog.KitKat.Controller.ManagerController;

import com.pentalog.KitKat.DTO.CreateProjectDTO;
import com.pentalog.KitKat.DTO.WorkerProjectDTO;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Service.ExcelUtil;
import com.pentalog.KitKat.Service.ProjectService;
import com.pentalog.KitKat.Service.UserService;
import com.pentalog.KitKat.Service.WorkersToManagerDashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        log.info("Received request to add user with id {} to project with id {}", body.getUserId(), body.getProjectId());
        try {
            return projectService.setProject(body.getUserId(), body.getProjectId());
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

    @PostMapping("/save-project")
    public ResponseEntity<?> saveProject(@RequestBody CreateProjectDTO project) {
        return projectService.saveProject(project);
    }

    @GetMapping("/excel/export")
    public ResponseEntity<byte[]> exportExcel() {
        try {
            List<User> users = userService.getAllUsers();
            ByteArrayOutputStream outputStream = ExcelUtil.exportUsersToExcel(users);

            // Set response headers for Excel file download
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=users.xlsx");
            headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/worker/project/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable("id") Integer id) {
        log.info("Received request to find user by id: {}", id);
        return ResponseEntity.ok(projectService.getProject(id));
    }
}
