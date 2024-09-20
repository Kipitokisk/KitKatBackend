package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.User.JwtTokenUtil;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.StatusRepository;
import com.pentalog.KitKat.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private UserRepository userRepository;
    private StatusRepository statusRepository;
    private JwtTokenUtil jwtTokenUtil;

    public CustomOAuth2UserService(UserRepository userRepository, StatusRepository statusRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauthUser = super.loadUser(userRequest);
        Map<String, Object> attributes = oauthUser.getAttributes();

        String email = (String) attributes.get("email");
        String firstName = (String) attributes.get("given_name");
        String lastName = (String) attributes.get("family_name");
        String oauthToken = userRequest.getAccessToken().getTokenValue();
        String googleUserId = (String) attributes.get("sub"); // Google user ID

        // Check if user exists by email or Google user ID
        User dbUser = userRepository.findUserByEmail(email);

        if (dbUser == null) {
            // User does not exist, create a new user
            dbUser = new User();
            dbUser.setFirstName(firstName);
            dbUser.setLastName(lastName);
            dbUser.setEmail(email);
            dbUser.setStatus(statusRepository.getReferenceById(2)); // Status ID for a new user
            dbUser.setOauthToken(oauthToken); // Store OAuth token for potential API calls
            userRepository.save(dbUser);

            log.info("New user registered: {}", email);
        } else {
            dbUser.setOauthToken(oauthToken); // Update OAuth token with the latest one
            userRepository.save(dbUser);

            log.info("Existing user logged in: {}", email);
        }

        // Generate JWT for the user
        String issuer = dbUser.getUserId().toString();
        String jwt = jwtTokenUtil.generateToken(issuer);

        // Create response containing user info and JWT
        Map<String, Object> res = new HashMap<>();
        res.put("id", dbUser.getUserId());
        res.put("email", dbUser.getEmail());
        res.put("jwt", jwt);

        log.info("User logged in successfully: {}", email);
        log.info("JWT: {}", jwt);


        // Return the response as a DefaultOAuth2User

        return new DefaultOAuth2User(Collections.emptySet(), attributes, "email");
    }
}
