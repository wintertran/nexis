package com.example.nexis.servlet;

import com.example.nexis.entity.Order;
import com.example.nexis.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", urlPatterns = "/orders")
public class OrderServlet extends HttpServlet {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        response.setContentType("application/json");

        if (userId != null) {
            List<Order> orders = orderRepository.findByUserId(userId);
            response.getWriter().write(orders.toString());
        } else {
            response.getWriter().write(orderRepository.findAll().toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        Double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

        Order order = new Order();
        order.setId(id);
        order.setTotalAmount(totalAmount);
        // Giả sử userId được ánh xạ đúng ở đây
        // order.setUser(...);

        orderRepository.save(order);

        response.setContentType("text/plain");
        response.getWriter().write("Order created successfully");
    }
}
