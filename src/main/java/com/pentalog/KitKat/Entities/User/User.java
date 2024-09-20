package com.pentalog.KitKat.Entities.User;

import com.pentalog.KitKat.Entities.*;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
    @NotNull
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
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @Column
    private String languages;
    @Column
    private BitSet cv;
    @OneToOne()
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToOne()
    @JoinColumn(name = "skill_rating_id")
    private SkillRating skillRating;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User managerId;
    @Column
    private String oauthToken;

    public User() {
    }

    public User(BitSet avatar, String firstName, String lastName, String email, BitSet password, Position position, Seniority seniority, City city, String languages, BitSet cv, Role role) {
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

    public void setSkillRating(SkillRating skillRating) {
        this.skillRating = skillRating;
    }

    public User getManagerId() {
        return managerId;
    }

    public void setManagerId(User managerId) {
        this.managerId = managerId;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    // Helper methods to convert the comma-separated string to a List of Integers
    public List<Integer> getLanguageIdList() {
        if (this.languages != null && !this.languages.isEmpty()) {
            return Arrays.stream(this.languages.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } else return new ArrayList<>();
    }

    public void setLanguageIdList(List<Integer> languageIdList) {
        this.languages = languageIdList.stream()
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
                ", city=" + city +
                ", languages='" + languages + '\'' +
                ", cv=" + cv +
                ", project=" + project +
                ", skillRating=" + skillRating +
                ", role=" + role +
                ", status=" + status +
                ", managerId=" + managerId +
                '}';
    }
}
