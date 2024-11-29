package com.pentalog.KitKat.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pentalog.KitKat.Entities.User.JwtTokenUtil;
import com.pentalog.KitKat.Entities.User.User;
import com.pentalog.KitKat.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Autowired
    public OAuth2AuthenticationSuccessHandler(JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        try {
            OAuth2AuthenticationToken oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oauth2User = oauth2AuthenticationToken.getPrincipal();

            String email = (String) oauth2User.getAttributes().get("email");

            // Retrieve the user from the database
            User dbUser = userRepository.findUserByEmail(email);
            if (dbUser == null) {
                log.error("User not found after OAuth2 authentication for: {}", email);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            // Generate JWT token for the user
            String jwt = jwtTokenUtil.generateToken(dbUser.getUserId().toString(), dbUser.getEmail(), dbUser.getRole().getName());

            // Prepare the response data (send user info and JWT to frontend)
            Map<String, Object> res = new HashMap<>();
            res.put("id", dbUser.getUserId());
            res.put("email", dbUser.getEmail());
            res.put("role", dbUser.getRole().getName());
            res.put("jwt", jwt);

            // Send JSON response back to frontend
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(res));

            log.info("User logged in successfully: {}", email);

        } catch (IOException e) {
            log.error("Error writing response: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

