package com.example.nexis.service;

import com.example.nexis.entity.Account;
import com.example.nexis.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public Optional<Account> getAccountByResetToken(String resetToken) {
        return accountRepository.findByResetToken(resetToken);
    }
}
