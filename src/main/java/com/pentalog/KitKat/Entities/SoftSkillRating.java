package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_soft_skill_ratings")
public class SoftSkillRating {
    @Id
    @GeneratedValue
    private Integer softSkillRatingId;
    @OneToOne()
    @JoinColumn(name = "worker_profile_info_id")
    private WorkerProfileInfo workerProfileInfo;
    @OneToOne()
    @JoinColumn(name = "soft_skill_id")
    private SoftSkill softSkill;
    @Column
    private Integer ratingSum;
    @Column
    private Integer nrOfReviews;

    public SoftSkillRating() {
    }

    public SoftSkillRating(WorkerProfileInfo workerProfileInfo, SoftSkill softSkill, Integer ratingSum) {
        this.workerProfileInfo = workerProfileInfo;
        this.softSkill = softSkill;
        this.ratingSum = ratingSum;
        this.nrOfReviews = 0;
    }

    public Integer getSoftSkillRatingId() {
        return softSkillRatingId;
    }

    public void setSoftSkillRatingId(Integer softSkillRatingId) {
        this.softSkillRatingId = softSkillRatingId;
    }

    public WorkerProfileInfo getWorkerProfileInfo() {
        return workerProfileInfo;
    }

    public void setUserId(WorkerProfileInfo workerProfileInfo) {
        this.workerProfileInfo = workerProfileInfo;
    }

    public SoftSkill getSoftSkill() {
        return softSkill;
    }

    public void setSoftSkillId(SoftSkill softSkill) {
        this.softSkill = softSkill;
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
        return "SoftSkillRating{" +
                "softSkillRatingId=" + softSkillRatingId +
                ", workerProfileInfo=" + workerProfileInfo +
                ", softSkill=" + softSkill +
                ", ratingSum=" + ratingSum +
                ", nrOfReviews=" + nrOfReviews +
                '}';
    }
}
