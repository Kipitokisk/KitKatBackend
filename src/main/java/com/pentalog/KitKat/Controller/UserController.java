package com.pentalog.KitKat.Controller;

import com.pentalog.KitKat.DTO.UserForRegistrationDTO;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

//    @GetMapping("/{user_id}")
//    public ResponseEntity<User> findUserById(@PathVariable("user_id") Integer id) {
//        Optional<User> user = userService.findUserById(id);
//        return user.map(ResponseEntity::ok) // If user is present, return 200 OK with the user
//                .orElseGet(() -> ResponseEntity.notFound().build()); // If not present, return 404 Not Found
//    }

    @GetMapping("/{user_email}")
    public User findUserByEmail(@PathVariable("user_email") String email) {
        return this.userService.findUserByEmail(email);
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
