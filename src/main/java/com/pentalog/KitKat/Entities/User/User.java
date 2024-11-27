package com.pentalog.KitKat.Entities.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    @ManyToOne
    @JoinColumn(name = "seniority_id")
    private Seniority seniority;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToMany
    @JoinTable(
            name = "user_languages",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    @JsonIgnore
    private List<Language> languages = new ArrayList<>(); // Store languages as a list
    @Column
    private BitSet cv;
    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<SkillRating> skillRating;
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

    public User(BitSet avatar, String firstName, String lastName, String email, BitSet password, Position position, Seniority seniority, City city, BitSet cv, Role role) {
        this.avatar = avatar;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.position = position;
        this.seniority = seniority;
        this.city = city;
        this.skillRating = new ArrayList<>();
        this.languages = new ArrayList<>();
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

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public void addLanguage(Language language) {
        this.languages.add(language);
    }

    public void removeLanguage(Language language) {
        this.languages.remove(language);
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

    public List<SkillRating> getSkillRating() {
        return skillRating;
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
        this.skillRating.add(skillRating);
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
