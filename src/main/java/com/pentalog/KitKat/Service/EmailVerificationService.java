package com.pentalog.KitKat.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class EmailVerificationService {
    @Autowired
    private EmailService emailService;
    private final RedisService redisService;

    public EmailVerificationService(RedisService redisService) {
        this.redisService = redisService;
    }

    public String generateVerificationCode(){
        return String.format("%06d", new Random().nextInt(999999));
    }

    public void sendVerificationCode(String email){
        log.info("Generating verification code for email: " + email);
        String code = generateVerificationCode();
        log.info("Sending verification code for email: " + email);
        emailService.sendEmail(email, "BenchHub: Verification code", "Here is your verification code: " + code);
        log.info("Storing in Redis verification code for email: " + email);
        redisService.storeVerificationCode(email, code);
    }

    public boolean checkVerificationCode(String email, String verificationCode){
        log.info("Checking verification code for email: " + email);
        String storedCode = redisService.getStoredCode(email);
        if(storedCode.equals(verificationCode)){
            log.info("Verification code is valid");
            return true;
        }
        else{
            log.info("Verification code is not valid");
            return false;
        }
    }
}
