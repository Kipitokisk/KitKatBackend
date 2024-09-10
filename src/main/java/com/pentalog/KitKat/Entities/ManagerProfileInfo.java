package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

import java.util.BitSet;

@Entity
@Table(name = "t_manager_profiles_info")
public class ManagerProfileInfo {
    @Id
    @GeneratedValue
    private Integer managerId;
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
    @Column
    private String languages;
    @Column
    private BitSet cv;

    public ManagerProfileInfo() {
    }

    public ManagerProfileInfo(BitSet avatar, String firstName, String lastName, String email, String position, String seniority, String country, String city, String languages, BitSet cv) {
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
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
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

    @Override
    public String toString() {
        return "ManagerProfileInfo{" +
                "managerId=" + managerId +
                ", avatar=" + avatar +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", seniority='" + seniority + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", languages='" + languages + '\'' +
                ", cv=" + cv +
                '}';
    }
}
