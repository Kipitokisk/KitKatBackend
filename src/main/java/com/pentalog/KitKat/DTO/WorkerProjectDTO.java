package com.pentalog.KitKat.DTO;

public class WorkerProjectDTO {
    private Integer workerId;
    private Integer projectId;

    public WorkerProjectDTO() {
    }

    public WorkerProjectDTO(Integer workerId, Integer projectId) {
        this.workerId = workerId;
        this.projectId = projectId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
