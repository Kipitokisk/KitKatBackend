package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_soft_skills")
public class SoftSkill {
    @Id
    @GeneratedValue
    private Integer softSkillId;
    @Column
    private String softSkillName;

    public SoftSkill() {
    }

    public SoftSkill(String softSkillName) {
        this.softSkillName = softSkillName;
    }

    public Integer getSoftSkillId() {
        return softSkillId;
    }

    public void setSoftSkillId(Integer softSkillId) {
        this.softSkillId = softSkillId;
    }

    public String getSoftSkillName() {
        return softSkillName;
    }

    public void setSoftSkillName(String softSkillName) {
        this.softSkillName = softSkillName;
    }

    @Override
    public String toString() {
        return "SoftSkill{" +
                "softSkillId=" + softSkillId +
                ", softSkillName='" + softSkillName + '\'' +
                '}';
    }
}
