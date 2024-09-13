package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.DTO.UserForRegistrationDTO;
import com.pentalog.KitKat.Entities.User;
import com.pentalog.KitKat.Repository.StatusRepository;
import com.pentalog.KitKat.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.BitSet;
import java.util.List;

@Service
//@RequiredArgsConstructor
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User registerNewUserAccount(UserForRegistrationDTO userForRegistrationDTO) throws Exception {
        if (userRepository.findByEmail(userForRegistrationDTO.getEmail()) != null) {
            throw new Exception("There is already an account with this email");
        }

        User user = new User();
        user.setAvatar(null);
        user.setFirstName(null);
        user.setLastName(null);
        user.setEmail(userForRegistrationDTO.getEmail());

        // Generate salt and hash password
        byte[] passwordSalt = passwordHashing.generateSalt();
        byte[] hashedPassword = passwordHashing.getPasswordHash(userForRegistrationDTO.getPassword(), passwordSalt);
        user.setPassword(BitSet.valueOf(hashedPassword)); // Store the hashed password

        user.setPosition(null);
        user.setSeniority(null);
        user.setCountry(null);
        user.setCity(null);
        user.setLanguagesId(null);
        user.setCv(null);
        user.setProject(null);
        user.setSkillInfo(null);
        user.setRole(null);
        user.setStatus(statusRepository.getReferenceById(2));

        return userRepository.save(user);
    }
}
