package com.pentalog.KitKat.DTO;

import java.time.LocalDateTime;

public class CreateProjectDTO {
    private String projectName;
    private Integer managerId;
    private LocalDateTime finishDate;
    private String description;

    public CreateProjectDTO() {}
    public CreateProjectDTO(String projectName, Integer managerId, LocalDateTime finishDate, String description) {
        this.projectName = projectName;
        this.managerId = managerId;
        this.finishDate = finishDate;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
