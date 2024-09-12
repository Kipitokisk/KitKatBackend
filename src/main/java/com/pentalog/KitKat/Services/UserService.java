package com.pentalog.KitKat.Services;

import com.pentalog.KitKat.DTOs.UserForRegistrationDTO;
import com.pentalog.KitKat.Entities.User;
import com.pentalog.KitKat.Interfaces.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;

    public User saveUser(User user) {
        User savedUser = userRepo.save(user);
        log.info("User with id: {} saved successfully", savedUser.getUserId());
        return savedUser;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    public User registerNewUserAccount(UserForRegistrationDTO userForRegistrationDTO) throws Exception {
        List<User> users = getAllUsers();
        for(User user : users){
            if (user.getEmail().equals(userForRegistrationDTO.getEmail())) {
                throw new Exception("There is already an account withthis email");
            }
        }

        User user = new User();
        user.setAvatar(null);
        user.setFirstName("");
        user.setLastName("");
        user.setEmail(userForRegistrationDTO.getEmail());
        user.setPassword(hashPassword(userForRegistrationDTO.getPassword()));
        user.setPosition(null);
        user.setSeniority(null);
        user.setCountry("");
        user.setCity("");
        user.setLanguagesId("");
        user.setCv(null);
        user.setProject(null);
        user.setSkillInfo(null);
        user.setRole(null);
        user.setStatus(null);

        return userRepo.save(user);
    }

    public static String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
