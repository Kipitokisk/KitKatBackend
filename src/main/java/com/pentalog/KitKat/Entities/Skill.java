package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skillId;
    @Column
    private String name;
    @ManyToOne()
    @JoinColumn(name = "skill_type_id")
    private SkillType skillType;

    public Skill() {
    }

    public Skill(Integer skillId, String name, SkillType skillType) {
        this.skillId = skillId;
        this.name = name;
        this.skillType = skillType;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillId=" + skillId +
                ", name='" + name + '\'' +
                ", skillType=" + skillType +
                '}';
    }
}
