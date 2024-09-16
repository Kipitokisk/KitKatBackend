package com.pentalog.KitKat.DTO;

public class ResetPasswordDTO {
    private String email;

    public ResetPasswordDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ResetPasswordDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
