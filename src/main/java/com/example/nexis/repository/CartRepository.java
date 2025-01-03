package com.example.nexis.repository;

import com.example.nexis.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    List<Cart> findByUserId(String userId); // Find all cart items by user ID
    void deleteByUserId(String userId);    // Delete all cart items by user ID
}
