package com.example.nexis.repository;

import com.example.nexis.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    // Lấy danh sách đơn hàng theo User ID
    List<Order> findByUserId(String userId);

    // Lấy danh sách đơn hàng theo trạng thái
    List<Order> findByStatus(Order status);
}
