package com.example.nexis.controller;

import com.example.nexis.entity.Order;
import com.example.nexis.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // Create a new order
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        order.setId(UUID.randomUUID().toString());
        order.setStatus(Order.Status.PENDING); // Default status
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.status(201).body(savedOrder);
    }

    // Get all orders
    @GetMapping("/get-all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok(orders);
    }

    // Get a specific order by ID
    @GetMapping("/get/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.status(404).body("Order not found");
        }
    }

    // Delete an order by ID
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable String orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return ResponseEntity.ok("Order deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Order not found");
        }
    }

    // Update the status of an order
    @PutMapping("/update-status/{orderId}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable String orderId, @RequestParam Order.Status status) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(status);
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(404).body("Order not found");
        }
    }
}
