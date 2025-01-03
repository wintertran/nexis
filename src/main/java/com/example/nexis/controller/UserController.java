package com.example.nexis.controller;

import com.example.nexis.entity.User;
import com.example.nexis.repository.UserRepository;
import com.example.nexis.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    // Lấy thông tin user hiện tại
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        String username = jwtService.extractUsername(request);
        User user = userRepository.findById(username).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(user);
    }

    // Chỉnh sửa thông tin user
    @PutMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser, HttpServletRequest request) {
        String username = jwtService.extractUsername(request);
        User user = userRepository.findById(username).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        userRepository.save(user);

        return ResponseEntity.ok("User updated successfully");
    }

    // Xóa user
    @DeleteMapping("/me")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) {
        String username = jwtService.extractUsername(request);
        User user = userRepository.findById(username).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        userRepository.delete(user);
        return ResponseEntity.ok("User deleted successfully");
    }
}
