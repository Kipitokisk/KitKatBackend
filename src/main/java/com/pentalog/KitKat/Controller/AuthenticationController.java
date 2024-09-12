package com.pentalog.KitKat.Controller;

import com.pentalog.KitKat.DTO.LoginDTO;
import com.pentalog.KitKat.Entities.User.JwtTokenUtil;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationController(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO body, HttpServletResponse response) {
        try {
            // Find user by email
            User user = userService.findByEmail(body.getEmail());
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found");
            }

            // Compare password
            if (!body.getPassword().equals(user.getPassword())) {
                return ResponseEntity.badRequest().body("Password incorrect");
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
