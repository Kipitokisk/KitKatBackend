package com.pentalog.KitKat.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.BitSet;

@Entity
@Table(name = "t_worker_profiles_info")
public class WorkerProfileInfo {
    @Id
    @GeneratedValue
    private Integer profileInfoId;
    @Column
    private BitSet avatar;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column
    private String position;
    @Column
    private String seniority;
    @Column
    private String country;
    @Column
    private String city;
    @ManyToOne()
    @JoinColumn(name = "manager_id")
    @JsonBackReference
    private ManagerProfileInfo manager;
    @Column
    private String languages;
    @Column
    private BitSet cv;
    @OneToOne()
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToOne()
    @JoinColumn(name = "skill_info_id")
    private SkillInfo skillInfo;
    @Column
    private Status status;


    public WorkerProfileInfo() {
    }

    public WorkerProfileInfo(BitSet avatar, String firstName, String lastName, String email, String position, String seniority, String country, String city, String languages, BitSet cv, SkillInfo skillInfo) {
        this.avatar = avatar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.seniority = seniority;
        this.country = country;
        this.city = city;
        this.languages = languages;
        this.cv = cv;
        this.skillInfo = skillInfo;
        this.status = Status.INACTIVE;
    }

    public SkillInfo getSkillInfo() {
        return skillInfo;
    }

    public void setSkillInfo(SkillInfo skillInfo) {
        this.skillInfo = skillInfo;
    }

    public Project getProjectId() {
        return project;
    }

    public void setProjectId(Project project) {
        this.project = project;
    }

    public Integer getProfileInfoId() {
        return profileInfoId;
    }

    public void setProfileInfoId(Integer profileInfoId) {
        this.profileInfoId = profileInfoId;
    }

    public BitSet getAvatar() {
        return avatar;
    }

    public void setAvatar(BitSet avatar) {
        this.avatar = avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ManagerProfileInfo getManagerId() {
        return manager;
    }

    public void setManagerId(ManagerProfileInfo manager) {
        this.manager = manager;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public BitSet getCv() {
        return cv;
    }

    public void setCv(BitSet cv) {
        this.cv = cv;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WorkerProfileInfo{" +
                "profileInfoId=" + profileInfoId +
                ", avatar=" + avatar +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", seniority='" + seniority + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", manager=" + manager +
                ", languages='" + languages + '\'' +
                ", cv=" + cv + '\'' +
                ", project=" + project + '\'' +
                ", skillInfo=" + skillInfo +
                '}';
    }
}
