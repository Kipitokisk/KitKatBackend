package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_tech_skills")
public class TechSkill {
    @Id
    @GeneratedValue
    private Integer techSkillId;
    @Column
    private String techSkillName;

    public TechSkill() {
    }

    public TechSkill(String techSkillName) {
        this.techSkillName = techSkillName;
    }

    public Integer getTechSkillId() {
        return techSkillId;
    }

    public void setTechSkillId(Integer techSkillId) {
        this.techSkillId = techSkillId;
    }

    public String getTechSkillName() {
        return techSkillName;
    }

    public void setTechSkillName(String techSkillName) {
        this.techSkillName = techSkillName;
    }

    @Override
    public String toString() {
        return "TechSkill{" +
                "techSkillId=" + techSkillId +
                ", techSkillName='" + techSkillName + '\'' +
                '}';
    }
}
