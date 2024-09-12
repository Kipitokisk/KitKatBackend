package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User findByEmail(String email) {return userRepository.findByEmail(email);}
}
