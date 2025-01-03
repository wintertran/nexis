package com.example.nexis.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY = "WinterSoldier2k3!@#SecureLongKey$%^";
    private static final long EXPIRATION_TIME = 86400000; // 1 ngày
    public String extractUsername(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("JWT Token is missing or invalid");
        }

        String token = authorizationHeader.substring(7); // Loại bỏ "Bearer " ở đầu
        return extractUsernameFromToken(token);
    }

    // Trích xuất username từ JWT token
    public String extractUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    // Generate JWT token từ đối tượng Account
    public String generateToken(com.example.nexis.entity.Account account) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", account.getUsername());
        claims.put("userId", account.getId());
        return createToken(claims, account.getUsername());
    }

    // Tạo JWT token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
