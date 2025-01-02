package com.example.nexis.repository;

import com.example.nexis.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    // Lấy danh sách role theo User ID
    Role findByUserId(String userId);
}
