package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_tech_skill_ratings")
public class TechSkillRating {
    @Id
    @GeneratedValue
    private Integer techSkillRatingId;
    @OneToOne()
    @JoinColumn(name = "worker_profile_info_id")
    private WorkerProfileInfo workerProfileInfo;
    @OneToOne()
    @JoinColumn(name = "tech_skill_id")
    private TechSkill techSkill;
    @Column
    private Integer ratingSum;
    @Column
    private Integer nrOfReviews;

    public TechSkillRating() {
    }

    public TechSkillRating(WorkerProfileInfo workerProfileInfo, TechSkill techSkill, Integer ratingSum) {
        this.workerProfileInfo = workerProfileInfo;
        this.techSkill = techSkill;
        this.ratingSum = ratingSum;
        this.nrOfReviews = 0;
    }

    public Integer getTechSkillRatingId() {
        return techSkillRatingId;
    }

    public void setTechSkillRatingId(Integer techSkillRatingId) {
        this.techSkillRatingId = techSkillRatingId;
    }

    public WorkerProfileInfo getWorkerProfileInfo() {
        return workerProfileInfo;
    }

    public void setUserId(WorkerProfileInfo workerProfileInfo) {
        this.workerProfileInfo = workerProfileInfo;
    }

    public TechSkill getTechSkill() {
        return techSkill;
    }

    public void setTechSkill(TechSkill techSkill) {
        this.techSkill = techSkill;
    }

    public Integer getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(Integer ratingSum) {
        this.ratingSum = ratingSum;
    }

    public Integer getNrOfReviews() {
        return nrOfReviews;
    }

    public void setNrOfReviews(Integer nrOfReviews) {
        this.nrOfReviews = nrOfReviews;
    }

    @Override
    public String toString() {
        return "TechSkillRating{" +
                "techSkillRatingId=" + techSkillRatingId +
                ", workerProfileInfo=" + workerProfileInfo +
                ", techSkill=" + techSkill +
                ", ratingSum=" + ratingSum +
                ", nrOfReviews=" + nrOfReviews +
                '}';
    }
}
