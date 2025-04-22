package com.pentalog.KitKat.Service;

import com.pentalog.KitKat.Entities.User.JwtTokenUtil;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.RoleRepository;
import com.pentalog.KitKat.Repository.StatusRepository;
import com.pentalog.KitKat.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final RoleRepository roleRepository;

    @Autowired
    public CustomOAuth2UserService(UserRepository userRepository, StatusRepository statusRepository, JwtTokenUtil jwtTokenUtil, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.roleRepository = roleRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauthUser = super.loadUser(userRequest); // Get OAuth2 user details
        Map<String, Object> attributes = oauthUser.getAttributes();

        String email = (String) attributes.get("email");
        String firstName = (String) attributes.get("given_name");
        String lastName = (String) attributes.get("family_name");
        String oauthToken = userRequest.getAccessToken().getTokenValue();

        log.info("OAuth2 login successful for user: {}", email);

        // Check if user exists in the database
        User dbUser = userRepository.findUserByEmail(email);
        if (dbUser == null) {
            // Register new user if not found
            dbUser = new User();
            dbUser.setEmail(email);
            dbUser.setFirstName(firstName);
            dbUser.setLastName(lastName);
            dbUser.setRole(roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role not found.")));
            dbUser.setStatus(statusRepository.findByName("PENDING").orElseThrow(() -> new RuntimeException("Status not found")));
            dbUser.setOauthToken(oauthToken); // Store OAuth token
            userRepository.save(dbUser);
            log.info("New user registered: {}", email);
        } else {
            dbUser.setOauthToken(oauthToken); // Update OAuth token if user exists
            userRepository.save(dbUser);

            log.info("Existing user logged in: {}", email);
        }

        String jwt = jwtTokenUtil.generateToken(dbUser.getUserId().toString(), dbUser.getEmail(), dbUser.getRole().getName());

        // Prepare response data including JWT
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("id", dbUser.getUserId());
        responseData.put("email", dbUser.getEmail());
        responseData.put("role", dbUser.getRole().getName());
        responseData.put("jwt", jwt);

        // Set JWT as a custom attribute in OAuth2User
        // This ensures the JWT is available to the OAuth2 authentication process
        Map<String, Object> updatedAttributes = new HashMap<>(oauthUser.getAttributes());
        updatedAttributes.put("jwt", jwt);

        // Return a new OAuth2User instance with the updated attributes (including JWT)
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(dbUser.getRole().getName())),
                updatedAttributes,
                "email" // The default name attribute for OAuth2User (email in this case)
        );
    }
}

