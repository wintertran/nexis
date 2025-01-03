package com.example.nexis.controller;

import com.example.nexis.entity.Role;
import com.example.nexis.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    // Gán quyền cho người dùng
    @PostMapping
    public ResponseEntity<?> assignRole(@RequestBody Role role) {
        roleRepository.save(role);
        return ResponseEntity.ok("Role assigned successfully");
    }

    // Lấy danh sách quyền của một người dùng
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRolesByUser(@PathVariable String userId) {
        Role role = roleRepository.findByUserId(userId);
        if (role == null) {
            return ResponseEntity.badRequest().body("Role not found");
        }
        return ResponseEntity.ok(role);
    }
}
