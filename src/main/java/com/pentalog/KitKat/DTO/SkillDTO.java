package com.pentalog.KitKat.DTO;

public class SkillDTO {
    private String skillName;
    private String skillType;

    public SkillDTO() {
    }

    public SkillDTO(String skillName, String skillType) {
        this.skillName = skillName;
        this.skillType = skillType;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    @Override
    public String toString() {
        return "SkillDTO{" +
                "skillName='" + skillName + '\'' +
                ", skillType='" + skillType + '\'' +
                '}';
    }
}
