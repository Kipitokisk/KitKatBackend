package com.pentalog.KitKat.Controller.AuthenticationController;

import com.pentalog.KitKat.DTO.LoginDTO;
import com.pentalog.KitKat.DTO.LoginOtpDTO;
import com.pentalog.KitKat.DTO.UserForRegistrationDTO;
import com.pentalog.KitKat.Entities.User.JwtTokenUtil;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Service.EmailVerificationService;
import com.pentalog.KitKat.Service.PasswordHashing;
import com.pentalog.KitKat.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
public class AuthenticationController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordHashing passwordHashing;
    private final EmailVerificationService emailVerificationService;

    public AuthenticationController(UserService userService, JwtTokenUtil jwtTokenUtil, PasswordHashing passwordHashing, EmailVerificationService emailVerificationService) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordHashing = passwordHashing;
        this.emailVerificationService = emailVerificationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO body, HttpServletResponse response) {
        log.debug("Login attempt initiated for email: {}", body.getEmail());

        try {
            log.debug("Searching for user by email: {}", body.getEmail());

            User user = userService.findUserByEmail(body.getEmail());
            if (user == null) {
                log.warn("User not found for email: {}", body.getEmail());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "User not found");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            log.debug("User found for email: {}", body.getEmail());

            byte[] hashedPassword = passwordHashing.getPasswordHash(body.getPassword());
            byte[] userPassword = user.getPassword().toByteArray();

            if (!Arrays.equals(hashedPassword, userPassword)) {
                log.warn("Password mismatch for user with email: {}", body.getEmail());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "Incorrect password");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            log.debug("Password verified for user with email: {}", body.getEmail());

            emailVerificationService.sendVerificationCode(body.getEmail());
            return ResponseEntity.ok("Verification code sent successfully");

        } catch (Exception e) {
            log.error("User login failed: {}", e.getMessage());

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during login");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping("/login-otp")
    public ResponseEntity<?> loginOTP(@RequestBody LoginOtpDTO body, HttpServletResponse response) {
        User user = userService.findUserByEmail(body.getEmail());
        if(emailVerificationService.checkVerificationCode(body.getEmail(), body.getVerificationCode())){
            String issuer = user.getUserId().toString();
            String jwt = jwtTokenUtil.generateToken(issuer, user.getEmail(), user.getRole().getName());

            Map<String, Object> res = new HashMap<>();
            res.put("id", user.getUserId());
            res.put("email", user.getEmail());
            res.put("role", user.getRole().getName());
            res.put("jwt", jwt);

            log.info("User logged successfully: {}", body.getEmail());

            return ResponseEntity.ok(res);
        }
        else {
            log.error("User login failed");

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during login");
            errorResponse.put("error", "User login failed");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserForRegistrationDTO user) {
        log.debug("Registering user account with information: {}", user);

        try {
            User savedUser = userService.registerNewUserAccount(user);
            log.info("User registered successfully: {}", savedUser.getEmail());

            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", "User registered successfully");
            successResponse.put("email", savedUser.getEmail());

            return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (Exception e) {
            log.error("User registration failed: {}", e.getMessage());

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "User registration failed");
            errorResponse.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }
}
