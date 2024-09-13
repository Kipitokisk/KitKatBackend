package com.pentalog.KitKat.Controller;

import com.pentalog.KitKat.DTO.UserForRegistrationDTO;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User saveUser(@Valid @RequestBody User user){
        return this.userService.saveUser(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserForRegistrationDTO user) {
        log.debug("Registering user account with information: {}", user);

        try {
            // Register the new user account and return a success message
            User savedUser = userService.registerNewUserAccount(user);
            log.info("User registered successfully: {}", savedUser.getEmail());

            // You can return a custom success message or the user object
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User registered successfully");
        } catch (Exception RegFailed) {
            log.error("User registration failed: {}", RegFailed.getMessage());

            // Return a 409 Conflict status if the user already exists
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User already exists");

        }
    }
}