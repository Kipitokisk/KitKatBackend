package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "t_user_verification_codes")
public class UserVerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String email;
    @Column
    private String verificationCode;
    @Column
    private LocalTime codeTime;

    public UserVerificationCode(String email, String verificationCode, LocalTime codeTime) {
        this.email = email;
        this.verificationCode = verificationCode;
        this.codeTime = codeTime;
    }

    public LocalTime getCodeTime() {
        return codeTime;
    }

    public void setCodeTime(LocalTime codeTime) {
        this.codeTime = codeTime;
    }

    public UserVerificationCode() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
