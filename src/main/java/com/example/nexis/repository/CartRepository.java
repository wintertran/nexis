package com.example.nexis.repository;

import com.example.nexis.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {
    // Lấy giỏ hàng của một User
    List<Cart> findByUserId(String userId);

    // Lấy sản phẩm trong giỏ hàng theo trạng thái
    List<Cart> findByStatus(Cart status);
}
