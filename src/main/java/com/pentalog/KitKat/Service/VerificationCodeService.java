package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.UserVerificationCode;
import com.pentalog.KitKat.Repository.UserVerificationCodeRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Service
public class VerificationCodeService {
    private final UserVerificationCodeRepository userVerificationCodeRepository;
    public VerificationCodeService(UserVerificationCodeRepository userVerificationCodeRepository) {
        this.userVerificationCodeRepository = userVerificationCodeRepository;
    }

    public void storeVerificationCode(String email, String verificationCode){
        userVerificationCodeRepository.save(new UserVerificationCode(email, verificationCode, LocalTime.now()));
    }

    public UserVerificationCode getStoredCode(String email){
        return userVerificationCodeRepository.findByEmail(email);
    }
    public void deleteVerificationCode(String email){
        userVerificationCodeRepository.delete(userVerificationCodeRepository.findByEmail(email));
    }

    public void deleteDuplicatedVerificationCodes(String email){
        List<UserVerificationCode> userVerificationCodes = userVerificationCodeRepository.findAll();
        for(UserVerificationCode userVerificationCode : userVerificationCodes){
            if(userVerificationCode.getEmail().equals(email)){
                userVerificationCodeRepository.delete(userVerificationCode);
            }
        }
    }
}
