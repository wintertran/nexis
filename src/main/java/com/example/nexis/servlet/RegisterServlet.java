package com.example.nexis.servlet;

import com.example.nexis.entity.Account;
import com.example.nexis.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Missing username or password\"}");
            return;
        }

        if (accountRepository.existsByUsername(username)) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().write("{\"error\": \"Username already exists\"}");
            return;
        }

        Account account = new Account();
        account.setId(java.util.UUID.randomUUID().toString());
        account.setUsername(username);
        account.setPasswordHash(password); // Replace with password hashing logic
        accountRepository.save(account);

        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"User registered successfully\"}");
    }
}
