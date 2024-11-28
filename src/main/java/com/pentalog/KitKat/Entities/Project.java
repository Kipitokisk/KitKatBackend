package com.pentalog.KitKat.Entities;

import com.pentalog.KitKat.Entities.User.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    @Column(unique = true)
    private String projectName;
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User manager;
    @Column
    private LocalDateTime startDate;
    @Column
    private LocalDateTime finishDate;
    @Column
    private Boolean status;
    @OneToMany(mappedBy = "project")
    private List<User> workers = new ArrayList<>();

    public Project() {
    }

    public Project(String projectName, User manager, LocalDateTime startDate, Boolean status) {
        this.projectName = projectName;
        this.manager = manager;
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

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
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

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", manager=" + manager +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", status=" + status +
                ", workers=" + workers +
                '}';
    }
}
