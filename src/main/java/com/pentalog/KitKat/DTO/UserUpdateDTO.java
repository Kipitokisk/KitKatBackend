package com.pentalog.KitKat.DTO;

import java.util.BitSet;

public class UserUpdateDTO {
    private Integer userId;
    private String firstName;
    private String lastName;
    private BitSet avatar;
    private String position;
    private String seniority;
    private String city;
    private String languages;
    private BitSet cv;
    private Integer managerId;


    public UserUpdateDTO() {
    }

    public UserUpdateDTO(Integer userId, String firstName, String lastName, BitSet avatar, String position, String seniority, String city, String languages, BitSet cv) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.position = position;
        this.seniority = seniority;
        this.city = city;
        this.languages = languages;
        this.cv = cv;

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public BitSet getAvatar() {
        return avatar;
    }

    public void setAvatar(BitSet avatar) {
        this.avatar = avatar;
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
}
