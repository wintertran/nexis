package com.example.nexis.service;

import com.example.nexis.entity.Account;
import com.example.nexis.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
