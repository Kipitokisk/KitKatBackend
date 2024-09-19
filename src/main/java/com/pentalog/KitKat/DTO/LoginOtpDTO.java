package com.pentalog.KitKat.DTO;

import org.springframework.data.redis.core.RedisHash;


public class LoginOtpDTO {
    private String email;
    private String verificationCode;

    public LoginOtpDTO() {
    }

    public LoginOtpDTO(String email, String verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
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

    @Override
    public String toString() {
        return "LoginOtpDTO{" +
                "email='" + email + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
