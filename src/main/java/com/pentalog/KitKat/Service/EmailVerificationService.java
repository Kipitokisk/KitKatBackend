package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.UserVerificationCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

@Slf4j
@Service
public class EmailVerificationService {
    @Autowired
    private EmailService emailService;
    private final VerificationCodeService verificationCodeService;

    public EmailVerificationService(VerificationCodeService verificationCodeService) {
        this.verificationCodeService = verificationCodeService;
    }

    public String generateVerificationCode(){
        return String.format("%06d", new Random().nextInt(999999));
    }

    public void sendVerificationCode(String email){
        log.info("Deleting duplicates from db of email: " + email);
        verificationCodeService.deleteDuplicatedVerificationCodes(email);
        log.info("Generating verification code for email: " + email);
        String code = generateVerificationCode();
        log.info("Sending verification code for email: " + email);
        emailService.sendEmail(email, "BenchHub: Verification code", "Here is your verification code: " + code);
        log.info("Storing in db verification code for email: " + email);
        verificationCodeService.storeVerificationCode(email, code);
    }

    public boolean checkVerificationCode(String email, String verificationCode){

        log.info("Checking verification code for email: " + email);
        UserVerificationCode userVerificationCode = verificationCodeService.getStoredCode(email);
        if(userVerificationCode.getVerificationCode().equals(verificationCode) && (Duration.between(userVerificationCode.getCodeTime(), LocalTime.now()).toMinutes() <= 5)){
            log.info("Verification code is valid");
            verificationCodeService.deleteVerificationCode(email);
            return true;
        }
        else{
            log.info("Verification code is not valid");
            return false;
        }
    }
}
