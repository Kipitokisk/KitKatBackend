package com.pentalog.KitKat.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pentalog.KitKat.Entities.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "t_skill_ratings")
public class SkillRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skillRatingId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    @Column
    private Integer ratingSum;
    @Column
    private Integer nrOfReviews;

    public SkillRating() {
    }

    public SkillRating(Integer skillRatingId, User user, Skill skill, Integer ratingSum, Integer nrOfReviews) {
        this.skillRatingId = skillRatingId;
        this.user = user;
        this.skill = skill;
        this.ratingSum = ratingSum;
        this.nrOfReviews = nrOfReviews;
    }

    public Integer getSkillRatingId() {
        return skillRatingId;
    }

    public void setSkillRatingId(Integer skillRatingId) {
        this.skillRatingId = skillRatingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
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

    public void updateRating(Integer newRating) {
        // Update the number of reviews
        this.nrOfReviews++;

        // Recalculate the average rating
        this.ratingSum = (this.ratingSum + newRating) / this.nrOfReviews;
    }

    @Override
    public String toString() {
        return "SkillRating{" +
                "skillRatingId=" + skillRatingId +
                ", user=" + user +
                ", skill=" + skill +
                ", ratingSum=" + ratingSum +
                ", nrOfReviews=" + nrOfReviews +
                '}';
    }
}
