package com.pentalog.KitKat.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_statuses")
public class Status {
    @Id
    private Integer statusId;
    @Column
    private String name;

    public Status() {
    }

    public Status(Integer statusId, String name) {
        this.statusId = statusId;
        this.name = name;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", name='" + name + '\'' +
                '}';
    }
}
