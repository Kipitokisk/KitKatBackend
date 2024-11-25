package com.pentalog.KitKat.Entities.User;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil {

    private static final Dotenv dotenv = Dotenv.configure().load();
    private static final String SECRET_KEY = dotenv.get("HASH_KEY");
    private static final long EXPIRATION_TIME = 3 * 24 * 60 * 60 * 1000;  // 3 days

    private final Key key;

    public JwtTokenUtil() {
        // Create a Key instance from the SECRET_KEY
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Method to generate a JWT token
    public static String generateToken(String userId, String email, String role) {
        return Jwts.builder()
                .setSubject(userId)  // Set user ID
                .claim("email", email)
                .claim("role", role)  // Role claim
                .setIssuedAt(new Date())  // Issue date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Expiry date
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)  // Sign with secret
                .compact();  // Build token
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        try {
            getClaimsFromToken(token);  // Validate and parse the claims
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract user ID from the token
    public String getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();  // User ID is the subject
    }

    // Extract role from the token
    public String getRoleFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (String) claims.get("role");  // Role is a claim
    }

    // Extract the claims from the token using parserBuilder (works with jjwt 3.x)
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()  // Use the updated parserBuilder
                .setSigningKey(key)  // Set the signing key (this is the key for HMAC)
                .build()  // Build the parser
                .parseClaimsJws(token)  // Parse the JWT token
                .getBody();  // Return the claims body
    }

    // Get authorities (roles) from the token
    public List<SimpleGrantedAuthority> getAuthoritiesFromToken(String token) {
        String role = getRoleFromToken(token);
        return List.of(new SimpleGrantedAuthority(role));  // Convert role to authority
    }
}
