package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

import java.util.BitSet;

@Entity
@Table(name = "t_passwords")
public class Password {
    @Id
    @GeneratedValue
    private Integer passwordId;
    @Column(unique = true)
    private String email;
    @Column
    private BitSet passwordHash;

    public Password() {
    }

    public Password(String email, BitSet passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Integer getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(Integer passwordId) {
        this.passwordId = passwordId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BitSet getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(BitSet passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "Password{" +
                "passwordId=" + passwordId +
                ", email='" + email + '\'' +
                ", passwordHash=" + passwordHash +
                '}';
    }
}
