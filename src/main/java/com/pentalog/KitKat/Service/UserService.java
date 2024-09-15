package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.DTO.UserForRegistrationDTO;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.StatusRepository;
import com.pentalog.KitKat.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.BitSet;
import java.util.List;

@Service
@Slf4j

public class UserService {
    private final UserRepository userRepository;
    private final PasswordHashing passwordHashing; // Inject PasswordHashing class
    private final StatusRepository statusRepository;


    public UserService(UserRepository userRepository, PasswordHashing passwordHashing, StatusRepository statusRepository) {
        this.userRepository = userRepository;
        this.passwordHashing = passwordHashing;
        this.statusRepository = statusRepository;
    }

    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        log.info("User with id: {} saved successfully", savedUser.getUserId());
        return savedUser;
    }
    public User findUserById(Integer id) {return userRepository.findUserById(id);}
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

}
