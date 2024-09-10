package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_projects")
public class Project {
    @Id
    @GeneratedValue
    private Integer projectId;
    @Column
    private String projectName;
    @OneToOne()
    @JoinColumn(name = "manager_profile_info_id")
    private ManagerProfileInfo managerProfileInfo;
    @Column
    private LocalDateTime startDate;
    @Column
    private Boolean status;

    public Project() {
    }

    public Project(String projectName, ManagerProfileInfo managerProfileInfo, LocalDateTime startDate, Boolean status) {
        this.projectName = projectName;
        this.managerProfileInfo = managerProfileInfo;
        this.startDate = startDate;
        this.status = status;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ManagerProfileInfo getManagerProfileInfo() {
        return managerProfileInfo;
    }

    public void setManagerProfileInfo(ManagerProfileInfo managerProfileInfo) {
        this.managerProfileInfo = managerProfileInfo;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", managerProfileInfo=" + managerProfileInfo +
                ", startDate=" + startDate +
                ", status=" + status +
                '}';
    }
}
