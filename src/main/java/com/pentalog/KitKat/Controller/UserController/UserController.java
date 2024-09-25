package com.pentalog.KitKat.Controller.UserController;

import com.pentalog.KitKat.DTO.*;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Service.ResetPasswordService;
import com.pentalog.KitKat.Service.PasswordHashing;
import com.pentalog.KitKat.Service.SkillRatingService;
import com.pentalog.KitKat.Service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PasswordHashing passwordHashing;
    private final ResetPasswordService resetPasswordService;
    private final SkillRatingService skillRatingService;

    public UserController(UserService userService, PasswordHashing passwordHashing, ResetPasswordService resetPasswordService, SkillRatingService skillRatingService) {
        this.userService = userService;
        this.passwordHashing = passwordHashing;
        this.resetPasswordService = resetPasswordService;
        this.skillRatingService = skillRatingService;
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

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        log.info("Received request to update user with ID: {}", userUpdateDTO.getUserId());

        try {
            return userService.updateUser(userUpdateDTO.getUserId(),
                    userUpdateDTO.getFirstName(),
                    userUpdateDTO.getLastName(),
                    userUpdateDTO.getAvatar(),
                    userUpdateDTO.getPosition(),
                    userUpdateDTO.getSeniority(),
                    userUpdateDTO.getCity(),
                    userUpdateDTO.getLanguages(),
                    userUpdateDTO.getCv());
        } catch (Exception e) {
            log.error("Error updating user with ID: {}", userUpdateDTO.getUserId(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user: " + e.getMessage());
        }
    }


    @PutMapping("/reset/{id}")
    public ResponseEntity<?> resetUser(@PathVariable("id") Integer userId) {
        return userService.resetUser(userId);
    }

    @GetMapping("/filter")
    public List<FilteredUser> filterUsers(
            @RequestParam(required = false) List<Integer> positionIds,
            @RequestParam(required = false) List<Integer> seniorityIds,
            @RequestParam(required = false) List<Integer> cityIds,
            @RequestParam(required = false) List<Integer> roleIds,
            @RequestParam(required = false) List<Integer> languages
    ) {
        return userService.filterUsers(positionIds, seniorityIds, cityIds, roleIds, languages);
    }

    @PostMapping("/save-skill-rating")
    public ResponseEntity<?> saveSkillRating(@RequestParam Integer userId,
                                             @RequestParam Integer skillId,
                                             @RequestParam Integer rating) {
        log.debug("Received request to save skill rating for user: {} and skill: {}", userId, skillId);
        return skillRatingService.saveSkillRating(userId, skillId, rating);
    }

    // Endpoint to submit a new rating for an existing skill rating
    @PostMapping("/submit-skill-rating")
    public ResponseEntity<?> submitRating(@RequestParam Integer userId,
                                          @RequestParam Integer skillId,
                                          @RequestParam Integer newRating) {
        log.debug("Received request to submit new rating for user: {} and skill: {}", userId, skillId);
        return skillRatingService.submitRating(userId, skillId, newRating);
    }

    @GetMapping("/without-project")
    public Integer getUsersWithoutProjectCount() {
        return userService.countUsersWithoutProject();
    }
}
