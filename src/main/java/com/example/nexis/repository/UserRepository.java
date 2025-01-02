package com.example.nexis.repository;

import com.example.nexis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    // Có thể thêm các truy vấn tùy chỉnh nếu cần
    User findByEmail(String email);
}
