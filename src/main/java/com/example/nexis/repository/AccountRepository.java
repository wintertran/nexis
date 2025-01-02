package com.example.nexis.repository;

import com.example.nexis.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    // Tìm Account dựa trên ResetToken
    Optional<Account> findByResetToken(String resetToken);

    // Tìm Account dựa trên Username
    Optional<Account> findByUsername(String username);
    boolean existsByUsername(String username);
}
