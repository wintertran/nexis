package com.example.nexis.controller;

import com.example.nexis.dto.CreateAccountDto;
import com.example.nexis.dto.UpdateAccountDto;
import com.example.nexis.entity.Account;
import com.example.nexis.entity.User;
import com.example.nexis.repository.AccountRepository;
import com.example.nexis.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.refresh.secret.key}")
    private String refreshSecretKey;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Đăng ký tài khoản
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateAccountDto request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }

        // Tạo User mới
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, request.getUsername(), null, null, null, null);
        userRepository.save(user);

        // Tạo Account mới
        Account account = new Account(UUID.randomUUID().toString(), user, request.getUsername(), passwordEncoder.encode(request.getPassword()), null);
        accountRepository.save(account);

        return ResponseEntity.ok("User registered successfully.");
    }

    // Đăng nhập tài khoản
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UpdateAccountDto request) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(request.getUsername());

        if (optionalAccount.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }

        Account account = optionalAccount.get();
        if (!passwordEncoder.matches(request.getPassword(), account.getPasswordHash())) {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }

        String accessToken = generateToken(account.getUser().getId(), secretKey, new Date(System.currentTimeMillis() + 3600000));
        String refreshToken = generateToken(account.getUser().getId(), refreshSecretKey, new Date(System.currentTimeMillis() + 604800000));

        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken, "Bearer", new Date(System.currentTimeMillis() + 3600000)));
    }

    // Reset mật khẩu
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody String newPassword) {
        Optional<Account> optionalAccount = accountRepository.findByResetToken(token);
        if (optionalAccount.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }

        Account account = optionalAccount.get();
        account.setPasswordHash(passwordEncoder.encode(newPassword));
        account.setResetPasswordToken(null);
        accountRepository.save(account);

        return ResponseEntity.ok("Password reset successfully.");
    }

    // Helper methods
    private String generateToken(String userId, String key, Date expiration) {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private static class TokenResponse {
        private final String accessToken;
        private final String refreshToken;
        private final String tokenType;
        private final Date expiredAt;

        public TokenResponse(String accessToken, String refreshToken, String tokenType, Date expiredAt) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.tokenType = tokenType;
            this.expiredAt = expiredAt;
        }

        // Getters
        public String getAccessToken() {
            return accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public String getTokenType() {
            return tokenType;
        }

        public Date getExpiredAt() {
            return expiredAt;
        }
    }
}
