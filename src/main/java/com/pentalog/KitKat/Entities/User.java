package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "t_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column
    private BitSet avatar;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column
    private BitSet password;
    @OneToOne
    @JoinColumn(name = "position_id")
    private Position position;
    @OneToOne
    @JoinColumn(name = "seniority_id")
    private Seniority seniority;
    @Column
    private String country;
    @Column
    private String city;
    @Column
    private String languagesId;
    @Column
    private BitSet cv;
    @OneToOne()
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToOne()
    @JoinColumn(name = "skill_rating_id")
    private SkillRating skillRating;
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public User() {
    }

    public User(BitSet avatar, String firstName, String lastName, String email, BitSet password, Position position, Seniority seniority, String country, String city, String languagesId, BitSet cv, Role role) {
        this.avatar = avatar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.position = position;
        this.seniority = seniority;
        this.country = country;
        this.city = city;
        this.languagesId = languagesId;
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

    public BitSet getPassword() {
        return password;
    }

    public void setPassword(BitSet password) {
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

    public String getLanguagesId() {
        return languagesId;
    }

    public void setLanguagesId(String languagesId) {
        this.languagesId = languagesId;
    }

    public BitSet getCv() {
        return cv;
    }

    public void setCv(BitSet cv) {
        this.cv = cv;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public SkillRating getSkillRating() {
        return skillRating;
    }

    public void setSkillInfo(SkillRating skillRating) {
        this.skillRating = skillRating;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Helper methods to convert the comma-separated string to a List of Integers
    public List getLanguageIdList() {
        return Arrays.stream(this.languagesId.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public void setLanguageIdList(List<Integer> languageIdList) {
        this.languagesId = languageIdList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", avatar=" + avatar +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", position=" + position +
                ", seniority=" + seniority +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", languagesId='" + languagesId + '\'' +
                ", cv=" + cv +
                ", project=" + project +
                ", skillRating=" + skillRating +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
