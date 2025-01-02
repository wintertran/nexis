package com.example.nexis.controller;

import com.example.nexis.entity.Account;
import com.example.nexis.entity.User;
import com.example.nexis.repository.AccountRepository;
import com.example.nexis.repository.UserRepository;
import com.example.nexis.service.EmailService;
import com.example.nexis.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmailService emailService;

    // 1. Đăng ký tài khoản
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String email = request.get("email");
        String name = request.get("name");

        if (accountRepository.findByUsername(username) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Tạo User tương ứng
        User user = new User();
        user.setId(username); // Sử dụng username làm ID
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        // Tạo Account
        Account account = new Account();
        account.setId(username);
        account.setUsername(username);
        account.setPasswordHash(passwordEncoder.encode(password));
        account.setUser(user);
        accountRepository.save(account);

        return ResponseEntity.ok("Account registered successfully");
    }

    // 2. Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Account account = accountRepository.findByUsername(username);
        if (account == null || !passwordEncoder.matches(password, account.getPasswordHash())) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        // Tạo JWT token
        String token = jwtService.generateToken(account);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    // 3. Quên mật khẩu
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody String email) {
        Account account = accountRepository.findByUsername(email);
        if (account == null) {
            return ResponseEntity.badRequest().body("Email not registered");
        }

        // Tạo token đặt lại mật khẩu
        String token = UUID.randomUUID().toString();
        account.setResetPasswordToken(token);
        account.setTokenExpiration(new Date(System.currentTimeMillis() + 3600000)); // 1 giờ
        accountRepository.save(account);

        // Gửi token qua email
        emailService.sendEmail(account.getUsername(), "Reset Password",
                "Here is your reset password token: " + token);

        return ResponseEntity.ok("Reset password token has been sent to your email.");
    }

    // 4. Đặt lại mật khẩu
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        Account account = accountRepository.findByResetPasswordToken(request.getToken());
        if (account == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        if (account.getTokenExpiration().before(new Date())) {
            return ResponseEntity.badRequest().body("Token has expired");
        }

        // Cập nhật mật khẩu mới
        account.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        account.setResetPasswordToken(null); // Xóa token sau khi sử dụng
        account.setTokenExpiration(null);
        accountRepository.save(account);

        return ResponseEntity.ok("Password has been reset successfully.");
    }
}

// DTO cho Reset Password
class ResetPasswordRequest {
    private String token;
    private String newPassword;

    // Getters và Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
