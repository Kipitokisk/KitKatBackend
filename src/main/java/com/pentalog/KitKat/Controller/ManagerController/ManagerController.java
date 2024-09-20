package com.pentalog.KitKat.Controller.ManagerController;

import com.pentalog.KitKat.DTO.WorkerToManagerDashboardDTO;
import com.pentalog.KitKat.Service.WorkersToManagerDashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manager")
public class ManagerController {

    private final WorkersToManagerDashboardService workersToManagerDashboardService;

    public ManagerController(WorkersToManagerDashboardService workersToManagerDashboardService) {
        this.workersToManagerDashboardService = workersToManagerDashboardService;
    }

    @GetMapping("/workers")
    public List<WorkerToManagerDashboardDTO> employeesDashboard() {
        log.info("Request to get all workers");
        return workersToManagerDashboardService.returnWorkersToManagerDashboard();
    }
}
