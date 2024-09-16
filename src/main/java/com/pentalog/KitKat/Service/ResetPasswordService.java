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

    @Autowired
    private JavaMailSender mailSender;
    private PasswordGeneratorService passwordGeneratorService;
    private final UserRepository userRepository;
    private final PasswordHashing passwordHashing;

    public ResetPasswordService(UserRepository userRepository, PasswordHashing passwordHashing) {
        this.userRepository = userRepository;
        this.passwordHashing = passwordHashing;
    }


    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    public String generatePassword() {

        return passwordGeneratorService.generatePassword(8);
    }

    @Transactional
    public void sendPassword(String email) throws Exception {
        if (userRepository.findUserByEmail(email) == null) {
            throw new Exception("There is no account with this email");
        }
        User user = userRepository.findUserByEmail(email);
        log.info("Generate password for user with email: " + email);
        String password = generatePassword();
        sendEmail(email, "New password", "Here is the new password for account: " + password);
        byte[] hashedPassword = passwordHashing.getPasswordHash(password);
        user.setPassword(BitSet.valueOf(hashedPassword)); // Store the hashed password
        userRepository.save(user);
        log.info("Save password for user with email: " + email);
    }
}
