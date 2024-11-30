package com.pentalog.KitKat.Entities.User;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.List;
@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Exclude specific paths from JWT authentication
        String requestURI = request.getRequestURI();
        if (isExcludedPath(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Process JWT authentication for other paths
        String token = getJwtFromRequest(request);

        if (token != null && jwtTokenUtil.validateToken(token)) {
            String userId = jwtTokenUtil.getUserIdFromToken(token);
            String role = jwtTokenUtil.getRoleFromToken(token);

            // Create an authentication token with the extracted user ID and role
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority(role)));

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    // Helper method to check excluded paths
    private boolean isExcludedPath(String requestURI) {
        // Add all paths that should not be processed by the JWT filter
        return requestURI.equals("/api/login-otp");
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // Extract the token
        }
        return null;
    }
}
