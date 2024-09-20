package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.DTO.CityDTO;
import com.pentalog.KitKat.DTO.UserForRegistrationDTO;
import com.pentalog.KitKat.Entities.*;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j

public class UserService {
    private final UserRepository userRepository;
    private final PasswordHashing passwordHashing; // Inject PasswordHashing class
    private final StatusRepository statusRepository;
    private final CityRepository cityRepository;
    private final PositionRepository positionRepository;
    private final SeniorityRepository seniorityRepository;


    public UserService(UserRepository userRepository, PasswordHashing passwordHashing, StatusRepository statusRepository, CityRepository cityRepository, PositionRepository positionRepository, SeniorityRepository seniorityRepository) {
        this.userRepository = userRepository;
        this.passwordHashing = passwordHashing;
        this.statusRepository = statusRepository;
        this.cityRepository = cityRepository;
        this.positionRepository = positionRepository;
        this.seniorityRepository = seniorityRepository;
    }

    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        log.info("User with id: {} saved successfully", savedUser.getUserId());
        return savedUser;
    }
    public Optional<User> findUserByOauthToken(String oauthToken) {return userRepository.findUserByOauthToken(oauthToken);}
    public Optional<User> findUserById(Integer id) {return userRepository.findById(id);}
    public User findUserByEmail(String email) {return userRepository.findUserByEmail(email);}
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerNewUserAccount(UserForRegistrationDTO userForRegistrationDTO) throws Exception {
        // Check if a user with the provided email already exists
        if (userRepository.findUserByEmail(userForRegistrationDTO.getEmail()) != null) {
            throw new Exception("There is already an account with this email");
        }

        // Create a new User object and set default fields
        User user = new User();
        user.setAvatar(null);
        user.setFirstName(null);
        user.setLastName(null);
        user.setEmail(userForRegistrationDTO.getEmail());

        // Hash password (using salt from the environment)
        byte[] hashedPassword = passwordHashing.getPasswordHash(userForRegistrationDTO.getPassword());
        user.setPassword(BitSet.valueOf(hashedPassword)); // Store the hashed password

        // Set other user properties
        user.setPosition(null);
        user.setSeniority(null);
        user.setCity(null);
        user.setLanguages(null);
        user.setCv(null);
        user.setProject(null);
        user.setSkillInfo(null);
        user.setRole(null);
        user.setStatus(statusRepository.getReferenceById(2));

        // Save the user and return the persisted entity
        return userRepository.save(user);
    }


    public ResponseEntity<?> updateUser(Integer userId, String firstName, String lastName,
                           BitSet avatar, String position, String seniority, String city,
                           String languages, BitSet cv) {

        User existingUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (firstName != null) existingUser.setFirstName(firstName);
        if (lastName != null) existingUser.setLastName(lastName);
        if (avatar != null) existingUser.setAvatar(avatar);
        if (city != null) {
            City cityEntity = cityRepository.findByCityName(city).orElseThrow(() -> new RuntimeException("City not found"));
            existingUser.setCity(cityEntity);
        }
        if (position != null) {
            Position positionEntity = positionRepository.findByName(position).orElseThrow(() -> new RuntimeException("Position not found"));
            existingUser.setPosition(positionEntity);
        }
        if (seniority != null) {
            Seniority seniorityEntity = seniorityRepository.findByName(seniority).orElseThrow(() -> new RuntimeException("Seniority not found"));
            existingUser.setSeniority(seniorityEntity);
        }
        if (languages != null) existingUser.setLanguages(languages);
        if (cv != null) existingUser.setCv(cv);

        userRepository.save(existingUser);
        return ResponseEntity.ok("User updated successfully");
    }

    public ResponseEntity<?> resetUser(Integer userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setFirstName(null);
        existingUser.setLastName(null);
        existingUser.setAvatar(null);
        existingUser.setCity(null);
        existingUser.setPosition(null);
        existingUser.setSeniority(null);
        existingUser.setLanguages(null);
        existingUser.setCv(null);
        userRepository.save(existingUser);
        return ResponseEntity.ok("User info reset successfully");
    }
}
