package com.pentalog.KitKat.DTO;

import com.pentalog.KitKat.Entities.*;
import com.pentalog.KitKat.Entities.User.User;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

public class UserDTO {
    private Integer userId;
    private BitSet avatar;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Position position;
    private Seniority seniority;
    private City city;
    private String languages;
    private BitSet cv;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(BitSet avatar, String firstName, String lastName, String email, String password, Position position, Seniority seniority, City city, String languages, BitSet cv, Role role) {
        this.avatar = avatar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.position = position;
        this.seniority = seniority;
        this.city = city;
        this.languages = languages;
        this.cv = cv;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
