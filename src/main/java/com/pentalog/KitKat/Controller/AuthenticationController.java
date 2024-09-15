package com.pentalog.KitKat.Controller;

import com.pentalog.KitKat.DTO.LoginDTO;
import com.pentalog.KitKat.Entities.User.JwtTokenUtil;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Service.PasswordHashing;
import com.pentalog.KitKat.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordHashing passwordHashing;

    public AuthenticationController(UserService userService, JwtTokenUtil jwtTokenUtil, PasswordHashing passwordHashing) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordHashing = passwordHashing;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO body, HttpServletResponse response) {
        try {
            // Find user by email
            User user = userService.findUserByEmail(body.getEmail());
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }

            // Hash the provided password using the stored salt from the environment
            byte[] hashedPassword = passwordHashing.getPasswordHash(body.getPassword());
            byte[] userPassword = user.getPassword().toByteArray();

            // Compare the stored password with the hashed password
            if (!Arrays.equals(hashedPassword, userPassword)) {
                return ResponseEntity.badRequest().body("Incorrect password");
            }

            // Generate JWT token
            String issuer = user.getUserId().toString();
            String jwt = jwtTokenUtil.generateToken(issuer);

            // Return success response with JWT token
            return ResponseEntity.ok("Bearer " + jwt);

        } catch (Exception e) {
            // Catch any unexpected exceptions and return internal server error response
            return ResponseEntity.status(500).body("An error occurred during login: " + e.getMessage());
        }
    }
}
