package com.example.nexis.servlet;

import com.example.nexis.entity.Order;
import com.example.nexis.repository.OrderRepository;
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
import java.util.UUID;

@WebServlet(name = "OrderServlet", urlPatterns = "/api/order/*")
public class OrderServlet extends HttpServlet {

    @Autowired
    private OrderRepository orderRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getPathInfo().equals("/create")) {
            Order order = objectMapper.readValue(request.getReader(), Order.class);
            order.setId(UUID.randomUUID().toString());
            order.setStatus(Order.Status.PENDING); // Default status
            Order savedOrder = orderRepository.save(order);

            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(savedOrder));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo.equals("/get-all")) {
            List<Order> orders = orderRepository.findAll();
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(orders));
        } else if (pathInfo.startsWith("/get/")) {
            String orderId = pathInfo.substring(5);
            Optional<Order> order = orderRepository.findById(orderId);

            if (order.isPresent()) {
                response.setContentType("application/json");
                response.getWriter().write(objectMapper.writeValueAsString(order.get()));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Order not found\"}");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo.startsWith("/delete/")) {
            String orderId = pathInfo.substring(8);

            if (orderRepository.existsById(orderId)) {
                orderRepository.deleteById(orderId);
                response.getWriter().write("{\"message\": \"Order deleted successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Order not found\"}");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo.startsWith("/update-status/")) {
            String orderId = pathInfo.substring(15);
            String status = request.getParameter("status");

            Optional<Order> orderOptional = orderRepository.findById(orderId);

            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setStatus(Order.Status.valueOf(status.toUpperCase()));
                orderRepository.save(order);

                response.setContentType("application/json");
                response.getWriter().write(objectMapper.writeValueAsString(order));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Order not found\"}");
            }
        }
    }
}
