package com.example.nexis.servlet;

import com.example.nexis.entity.Product;
import com.example.nexis.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ProductServlet", urlPatterns = "/api/Product/*")
public class ProductServlet extends HttpServlet {

    @Autowired
    private ProductRepository productRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/getProductByPaging")) {
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            List<Product> products = productRepository.findAll(PageRequest.of(page, size)).getContent();
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(products));
        } else if (pathInfo.startsWith("/search")) {
            String keyword = request.getParameter("keyword");
            List<Product> products = productRepository.findByNameContainingOrDescriptionContaining(keyword, keyword);
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(products));
        } else if (pathInfo.startsWith("/")) {
            String id = pathInfo.substring(1);
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                response.setContentType("application/json");
                response.getWriter().write(objectMapper.writeValueAsString(product.get()));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Product not found\"}");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
