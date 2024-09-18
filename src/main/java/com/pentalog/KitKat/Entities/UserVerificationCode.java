package com.pentalog.KitKat.Entities;

import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("t_user_verification_codes")
public class UserVerificationCode {
    @Id
    private String id;
    private String email;
    private String verificationCode;

    public UserVerificationCode(String email, String verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
