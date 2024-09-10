    package com.pentalog.KitKat.Entities;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "t_skills_info")
    public class SkillInfo {
        @Id
        @GeneratedValue
        private Integer skillId;
        @OneToOne()
        @JoinColumn(name = "tech_skill_rating_id")
        private TechSkillRating techSkillRating;
        @OneToOne()
        @JoinColumn(name = "soft_skill_rating_id")
        private SoftSkillRating softSkillRating;

        public SkillInfo() {
        }

        public SkillInfo(TechSkillRating techSkillRating, SoftSkillRating softSkillRating) {
            this.techSkillRating = techSkillRating;
            this.softSkillRating = softSkillRating;
        }

        public Integer getSkillId() {
            return skillId;
        }

        public void setSkillId(Integer skillId) {
            this.skillId = skillId;
        }

        public TechSkillRating getTechSkillRating() {
            return techSkillRating;
        }

        public void setTechSkillRating(Integer techSkillRatingId) {
            this.techSkillRating = techSkillRating;
        }

        public SoftSkillRating getSoftSkillRating() {
            return softSkillRating;
        }

        public void setSoftSkillRating(SoftSkillRating softSkillRating) {
            this.softSkillRating = softSkillRating;
        }

        @Override
        public String toString() {
            return "SkillInfo{" +
                    "skillId=" + skillId +
                    ", techSkillRating=" + techSkillRating +
                    ", softSkillRating=" + softSkillRating +
                    '}';
        }
    }
