package com.example.nexis.servlet;

import com.example.nexis.dto.UpdateCartDto;
import com.example.nexis.entity.Cart;
import com.example.nexis.repository.CartRepository;
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
import java.util.Optional;

@WebServlet(name = "CartServlet", urlPatterns = "/api/Cart/*")
public class CartServlet extends HttpServlet {

    @Autowired
    private CartRepository cartRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        List<Cart> cartItems = cartRepository.findByUserId(userId);

        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(cartItems));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UpdateCartDto updateRequest = objectMapper.readValue(request.getReader(), UpdateCartDto.class);

        Optional<Cart> cartOptional = cartRepository.findById(updateRequest.getProductId());
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();

            if ("add".equalsIgnoreCase(updateRequest.getActionType())) {
                cart.setQuantity(cart.getQuantity() + updateRequest.getQuantityChange());
            } else if ("remove".equalsIgnoreCase(updateRequest.getActionType())) {
                cart.setQuantity(Math.max(0, cart.getQuantity() - updateRequest.getQuantityChange()));
            }

            cartRepository.save(cart);
            response.getWriter().write("{\"message\": \"Cart updated successfully\"}");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\": \"Cart item not found\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        cartRepository.deleteByUserId(userId);

        response.getWriter().write("{\"message\": \"Cart reset successfully\"}");
    }
}
