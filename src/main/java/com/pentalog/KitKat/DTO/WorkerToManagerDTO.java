package com.pentalog.KitKat.DTO;

import com.pentalog.KitKat.Entities.Project;

import java.util.BitSet;
import java.util.List;

public class WorkerToManagerDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private BitSet avatar;
    private String seniority;
    private String role;
    private List<String> languages;
    private List<String> skills;
    private String city;
    private BitSet cv;
    private String project;


    public WorkerToManagerDTO() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BitSet getCv() {
        return cv;
    }

    public void setCv(BitSet cv) {
        this.cv = cv;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BitSet getAvatar() {
        return avatar;
    }

    public void setAvatar(BitSet avatar) {
        this.avatar = avatar;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
