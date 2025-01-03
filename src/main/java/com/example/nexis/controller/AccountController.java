package com.example.nexis.controller;

import com.example.nexis.dto.CreateAccountDto;
import com.example.nexis.entity.Account;
import com.example.nexis.entity.User;
import com.example.nexis.service.AccountService;
import com.example.nexis.service.UserService;
import com.example.nexis.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final String secretKey = "WinterSoldier2k3!@#SecureLongKey$%^";

    // -------------------------------
    // Registration Endpoint
    // -------------------------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateAccountDto request) {
        try {
            // Debug logs
            System.out.println("Register: Username = " + request.getUsername());
            System.out.println("Register: Password = " + request.getPassword());

            // Validate input
            if (request.getUsername() == null || request.getUsername().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username cannot be null or empty.");
            }
            if (request.getPassword() == null || request.getPassword().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password cannot be null or empty.");
            }

            // Check if the username already exists
            if (accountService.getAccountByUsername(request.getUsername()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists.");
            }

            // Create a new user entity
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName(request.getUsername());
            userService.createUser(user);

            // Create a new account entity
            Account account = new Account();
            account.setId(UUID.randomUUID().toString());
            account.setUsername(request.getUsername());
            account.setPasswordHash(passwordEncoder.encode(request.getPassword())); // Hash the password
            account.setUserId(user.getId()); // Link the account to the user
            accountService.createAccount(account);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    // -------------------------------
    // Login Endpoint
    // -------------------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CreateAccountDto request) {
        try {
            // Debug logs
            System.out.println("Login: Username = " + request.getUsername());
            System.out.println("Login: Password = " + request.getPassword());

            // Validate input
            if (request.getUsername() == null || request.getUsername().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username cannot be null or empty.");
            }
            if (request.getPassword() == null || request.getPassword().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password cannot be null or empty.");
            }

            // Find the account by username
            Optional<Account> accountOptional = accountService.getAccountByUsername(request.getUsername());
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();

                // Check if the password matches
                if (passwordEncoder.matches(request.getPassword(), account.getPasswordHash())) {
                    // Generate JWT token
                    String token = jwtUtil.generateToken(account.getUserId(), secretKey, 3600000); // 1 hour

                    // Return token response
                    Map<String, Object> response = new HashMap<>();
                    response.put("AccessToken", token);
                    response.put("TokenType", "Bearer");
                    response.put("ExpiresAt", System.currentTimeMillis() + 3600000);

                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}
