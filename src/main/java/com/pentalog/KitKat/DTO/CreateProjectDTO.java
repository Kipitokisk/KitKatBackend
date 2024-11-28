package com.pentalog.KitKat.DTO;

import java.time.LocalDateTime;

public class CreateProjectDTO {
    private String projectName;
    private Integer managerId;
    private LocalDateTime finishDate;
    public CreateProjectDTO() {}
    public CreateProjectDTO(String projectName, Integer managerId, LocalDateTime finishDate) {
        this.projectName = projectName;
        this.managerId = managerId;
        this.finishDate = finishDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }
}
