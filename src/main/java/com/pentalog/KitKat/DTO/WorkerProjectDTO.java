package com.pentalog.KitKat.DTO;

public class WorkerProjectDTO {
    private Integer workerId;
    private String projectName;

    public WorkerProjectDTO() {
    }

    public WorkerProjectDTO(Integer workerId, String projectName) {
        this.workerId = workerId;
        this.projectName = projectName;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
