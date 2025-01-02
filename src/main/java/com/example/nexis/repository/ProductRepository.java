package com.example.nexis.repository;

import com.example.nexis.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    // Lấy danh sách sản phẩm theo Category ID
    List<Product> findByCategoryId(String categoryId);

    // Tìm sản phẩm theo tên
    List<Product> findByNameContainingIgnoreCase(String name);
}
