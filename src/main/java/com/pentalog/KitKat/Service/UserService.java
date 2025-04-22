package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.DTO.UserForRegistrationDTO;
import com.pentalog.KitKat.Entities.*;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    private final LanguageRepository languageRepository;
    private final RoleRepository roleRepository;


    public UserService(UserRepository userRepository, PasswordHashing passwordHashing, StatusRepository statusRepository, CityRepository cityRepository, PositionRepository positionRepository, SeniorityRepository seniorityRepository, LanguageRepository languageRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordHashing = passwordHashing;
        this.statusRepository = statusRepository;
        this.cityRepository = cityRepository;
        this.positionRepository = positionRepository;
        this.seniorityRepository = seniorityRepository;
        this.languageRepository = languageRepository;
        this.roleRepository = roleRepository;
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
        if (userRepository.findUserByEmail(userForRegistrationDTO.getEmail()) != null) {
            throw new Exception("There is already an account with this email");
        }

        User user = new User();
        user.setAvatar(null);
        user.setFirstName(null);
        user.setLastName(null);
        user.setEmail(userForRegistrationDTO.getEmail());

        byte[] hashedPassword = passwordHashing.getPasswordHash(userForRegistrationDTO.getPassword());
        user.setPassword(BitSet.valueOf(hashedPassword));

        user.setPosition(null);
        user.setSeniority(null);
        user.setCity(null);
        user.setLanguages(null);
        user.setCv(null);
        user.setProject(null);
        user.setSkillRating(null);
        user.setRole(roleRepository.findByName("ROLE_USER").orElseThrow(() -> new Exception("Role Not Found")));
        user.setStatus(statusRepository.findByName("PENDING").orElseThrow(() -> new RuntimeException("Status not found")));

        return userRepository.save(user);
    }


    public ResponseEntity<?> updateUser(Integer userId, String firstName, String lastName,
                                        BitSet avatar, String position, String seniority, String city,
                                        String languages, BitSet cv) {

        log.info("Updating user with ID: {}", userId);

        User existingUser = userRepository.findById(userId).orElseThrow(() -> {
            log.error("User with ID {} not found", userId);
            return new RuntimeException("User not found");
        });

        if (firstName != null) existingUser.setFirstName(firstName);
        if (lastName != null) existingUser.setLastName(lastName);
        if (avatar != null) existingUser.setAvatar(avatar);

        if (city != null) {
            log.info("Updating city for user ID {}", userId);
            City cityEntity = cityRepository.findByCityName(city).orElseThrow(() -> {
                log.error("City '{}' not found for user ID {}", city, userId);
                return new RuntimeException("City not found");
            });
            existingUser.setCity(cityEntity);
        }

        if (position != null) {
            log.info("Updating position for user ID {}", userId);
            Position positionEntity = positionRepository.findByName(position).orElseThrow(() -> {
                log.error("Position '{}' not found for user ID {}", position, userId);
                return new RuntimeException("Position not found");
            });
            existingUser.setPosition(positionEntity);
        }

        if (seniority != null) {
            log.info("Updating seniority for user ID {}", userId);
            Seniority seniorityEntity = seniorityRepository.findByName(seniority).orElseThrow(() -> {
                log.error("Seniority '{}' not found for user ID {}", seniority, userId);
                return new RuntimeException("Seniority not found");
            });
            existingUser.setSeniority(seniorityEntity);
        }

        if (languages != null) {
            log.info("Updating languages for user ID {}", userId);
            String[] languageIds = languages.split(",");
            List<Language> languageList = new ArrayList<>();
            for (String id : languageIds) {
                Language languageEntity = languageRepository.findById(Integer.valueOf(id)).orElseThrow(() -> {
                    log.error("Language with ID '{}' not found for user ID {}", id, userId);
                    return new RuntimeException("Language not found");
                });
                languageList.add(languageEntity);
            }
            existingUser.setLanguages(languageList);
        }

        if (cv != null) existingUser.setCv(cv);

        userRepository.save(existingUser);
        log.info("User with ID {} updated successfully", userId);

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

    public Integer countUsersWithoutProject() {
        return userRepository.countByProjectIsNull();
    }

    public Integer getUserCountWithoutProjectByCountry(String countryName) {
        List<User> users = userRepository.findAll();

        return (int) users.stream()
                .filter(user -> user.getProject() == null) // Filter users without a project
                .filter(user -> user.getCity() != null && user.getCity().getCountry() != null) // Ensure city and country are not null
                .filter(user -> user.getCity().getCountry().getCountryName().equalsIgnoreCase(countryName)) // Filter by country name
                .count();
    }

    public User addLanguageToUser(Integer userId, Integer languageId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Language language = languageRepository.findById(languageId).orElseThrow(() -> new RuntimeException("Language not found"));

        user.addLanguage(language);
        return userRepository.save(user);
    }

    public Page<User> searchUsers(List<String> position, List<String> seniority, List<String> country,
                                  List<String> skill, List<String> languages, List<String> roles, Pageable pageable) {
        Specification<User> specification = UserSpecification.combinedFilter(position, seniority, country, skill, languages, roles);
        return userRepository.findAll(specification, pageable);
    }


    public User acceptUser(Integer userId, String role) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(statusRepository.findByName("ACCEPTED").orElseThrow(() -> new RuntimeException("Status not found")));
        user.setRole(roleRepository.findByName(role).orElseThrow(() -> new RuntimeException("Role not found")));
        return userRepository.save(user);
    }
}
