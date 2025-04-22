package com.pentalog.KitKat.DTO;

public class WorkerProjectDTO {
    private Integer userId;
    private Integer projectId;

    public WorkerProjectDTO() {
    }

    public WorkerProjectDTO(Integer userId, Integer projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
