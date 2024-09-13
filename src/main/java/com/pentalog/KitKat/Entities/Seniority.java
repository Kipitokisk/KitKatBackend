package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_seniority")
public class Seniority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seniortyId;
    @Column(unique = true)
    private String name;

    public Seniority() {
    }

    public Seniority(Integer seniortyId, String name) {
        this.seniortyId = seniortyId;
        this.name = name;
    }

    public Integer getSeniortyId() {
        return seniortyId;
    }

    public void setSeniortyId(Integer seniortyId) {
        this.seniortyId = seniortyId;
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
                "seniortyId=" + seniortyId +
                ", name='" + name + '\'' +
                '}';
    }
}
