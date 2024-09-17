package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.DTO.ResetPasswordDTO;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.BitSet;

@Slf4j
@Service
public class ResetPasswordService {
;
    private final EmailService emailService;
    private final PasswordGeneratorService passwordGeneratorService;
    private final UserRepository userRepository;
    private final PasswordHashing passwordHashing;

    public ResetPasswordService(EmailService emailService, UserRepository userRepository, PasswordHashing passwordHashing, PasswordGeneratorService passwordGeneratorService) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.passwordHashing = passwordHashing;
        this.passwordGeneratorService = passwordGeneratorService;
    }

    public String generatePassword() {
        return passwordGeneratorService.generatePassword(8);
    }

    @Transactional
    public void sendPassword(String email) throws Exception {
        if (userRepository.findUserByEmail(email) == null) {
            log.error("There is no account with this email: " + email);
            throw new Exception("There is no account with this email");
        }
        User user = userRepository.findUserByEmail(email);
        log.info("Generate password for user with email: " + email);
        String password = generatePassword();
        log.info("Send email to user: " + email);
        emailService.sendEmail(email, "BenchHub: Password reset", "Hello " + email + ",\nYour password was reset. Here is a new password: " + password + "\n\nSincerely,\nBenchHUB team");
        byte[] hashedPassword = passwordHashing.getPasswordHash(password);
        user.setPassword(BitSet.valueOf(hashedPassword)); // Store the hashed password
        userRepository.save(user);
        log.info("Save password for user with email: " + email);
    }
}
