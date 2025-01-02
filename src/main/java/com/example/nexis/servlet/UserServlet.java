package com.example.nexis.servlet;

import com.example.nexis.entity.User;
import com.example.nexis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        List<User> users = userRepository.findAll();
        response.getWriter().write(users.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);

        userRepository.save(user);

        response.setContentType("text/plain");
        response.getWriter().write("User created successfully");
    }
}
