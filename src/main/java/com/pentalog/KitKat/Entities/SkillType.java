package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_skill_types")
public class SkillType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skillTypeId;
    @Column
    private String name;

    public SkillType() {
    }

    public SkillType(Integer skillTypeId, String name) {
        this.skillTypeId = skillTypeId;
        this.name = name;
    }

    public Integer getSkillTypeId() {
        return skillTypeId;
    }

    public void setSkillTypeId(Integer skillTypeId) {
        this.skillTypeId = skillTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SkillTypes{" +
                "skillTypeId=" + skillTypeId +
                ", name='" + name + '\'' +
                '}';
    }
}
