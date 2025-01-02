package com.example.nexis.repository;

import com.example.nexis.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);
    Account findByResetPasswordToken(String resetPasswordToken);
}
