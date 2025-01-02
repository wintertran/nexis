package com.example.nexis.controller;

import com.example.nexis.entity.Order;
import com.example.nexis.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // Lấy danh sách đơn hàng của người dùng hiện tại
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    // Lấy thông tin chi tiết của một đơn hàng
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return ResponseEntity.badRequest().body("Order not found");
        }
        return ResponseEntity.ok(order);
    }

    // Tạo đơn hàng mới
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        orderRepository.save(order);
        return ResponseEntity.ok("Order created successfully");
    }

    // Cập nhật trạng thái đơn hàng
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable String id, @RequestBody String status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return ResponseEntity.badRequest().body("Order not found");
        }

        order.setStatus(Order.Status.valueOf(status.toUpperCase()));
        orderRepository.save(order);

        return ResponseEntity.ok("Order status updated successfully");
    }

    // Xóa đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return ResponseEntity.badRequest().body("Order not found");
        }
        orderRepository.delete(order);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
