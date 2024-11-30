package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_seniority")
public class Seniority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seniorityId;
    @Column(unique = true)
    private String name;

    public Seniority() {
    }

    public Seniority(Integer seniorityId, String name) {
        this.seniorityId = seniorityId;
        this.name = name;
    }

    public Integer getSeniorityId() {
        return seniorityId;
    }

    public void setSeniorityId(Integer seniorityId) {
        this.seniorityId = seniorityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Seniority{" +
                "seniorityId=" + seniorityId +
                ", name='" + name + '\'' +
                '}';
    }
}
