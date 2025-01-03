package com.example.nexis.servlet;

import com.example.nexis.entity.Brand;
import com.example.nexis.repository.BrandRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BrandServlet", urlPatterns = "/api/Brand/list")
public class BrandServlet extends HttpServlet {

    @Autowired
    private BrandRepository brandRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        // Enable Spring Autowiring for this Servlet
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Brand> brands = brandRepository.findAll();
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(brands));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Unable to fetch brands\"}");
        }
    }
}
