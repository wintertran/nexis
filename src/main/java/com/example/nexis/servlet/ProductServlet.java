package com.example.nexis.servlet;

import com.example.nexis.entity.Product;
import com.example.nexis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    @Autowired
    private ProductRepository productRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setContentType("application/json");

        if (id != null) {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                response.getWriter().write(product.get().toString());
            } else {
                response.getWriter().write("{\"error\":\"Product not found\"}");
            }
        } else {
            response.getWriter().write(productRepository.findAll().toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);

        productRepository.save(product);

        response.setContentType("text/plain");
        response.getWriter().write("Product created successfully");
    }
}
