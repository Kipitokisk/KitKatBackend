package com.pentalog.KitKat.Controller.UserController;

import com.pentalog.KitKat.DTO.ResetPasswordDTO;
import com.pentalog.KitKat.DTO.UserDTO;
import com.pentalog.KitKat.DTO.UserForRegistrationDTO;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Service.ResetPasswordService;
import com.pentalog.KitKat.Service.PasswordHashing;
import com.pentalog.KitKat.Service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PasswordHashing passwordHashing;
    private final ResetPasswordService resetPasswordService;

    public UserController(UserService userService, PasswordHashing passwordHashing, ResetPasswordService resetPasswordService) {
        this.userService = userService;
        this.passwordHashing = passwordHashing;
        this.resetPasswordService = resetPasswordService;
    }

    @PostMapping("/create")
    public User saveUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
        log.info("Received request to create user with email: {}", userDTO.getEmail());

        try {
            User user = new User();
            user.setAvatar(userDTO.getAvatar());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());

            byte[] hashedPassword = passwordHashing.getPasswordHash(userDTO.getPassword());
            user.setPassword(BitSet.valueOf(hashedPassword)); // Store the hashed password

            user.setPosition(userDTO.getPosition());
            user.setSeniority(userDTO.getSeniority());
            user.setCity(userDTO.getCity());
            user.setLanguages(userDTO.getLanguages());
            user.setCv(userDTO.getCv());
            user.setRole(userDTO.getRole());

            User savedUser = this.userService.saveUser(user);

            log.info("User successfully created with email: {}", userDTO.getEmail());
            return savedUser;
        } catch (Exception e) {
            log.error("Error creating user with email: {}. Exception: {}", userDTO.getEmail(), e.getMessage());
            throw e; // Re-throw the exception after logging
        }
    }

//    @GetMapping("/{user_id}")
//    public ResponseEntity<User> findUserById(@PathVariable("user_id") Integer id) {
//        Optional<User> user = userService.findUserById(id);
//        return user.map(ResponseEntity::ok) // If user is present, return 200 OK with the user
//                .orElseGet(() -> ResponseEntity.notFound().build()); // If not present, return 404 Not Found
//    }

    @GetMapping("/{user_email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable("user_email") String email) {
        log.info("Received request to find user by email: {}", email);

        User user = userService.findUserByEmail(email);

        if (user == null) {
            log.warn("No user found with email: {}", email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with email '" + email + "' not found.");
        } else {
            log.info("User found with email: {}", email);
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> testEmail(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO) throws Exception {
        try {
            log.debug("Searching for user by email: {}", resetPasswordDTO.getEmail());
            User user = userService.findUserByEmail(resetPasswordDTO.getEmail());
            if (user == null) {
                log.warn("User not found for email: {}", resetPasswordDTO.getEmail());
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("message", "User not found");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            log.info("Received request to reset password for user with email: {}", resetPasswordDTO.getEmail());
            resetPasswordService.sendPassword(user);
            return ResponseEntity.ok("New password sent.");
        }
        catch (Exception e){
            log.error("Sending email failed {}", e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred during sending email");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }


}
