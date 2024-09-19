package com.pentalog.KitKat.Repository;

import com.pentalog.KitKat.Entities.UserVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationCodeRepository extends JpaRepository<UserVerificationCode, Integer> {
    UserVerificationCode findByEmail(String email);
}
