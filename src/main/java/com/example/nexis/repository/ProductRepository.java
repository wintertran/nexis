package com.example.nexis.repository;

import com.example.nexis.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // Search products by name or description
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);

    // For pagination
    Page<Product> findAll(Pageable pageable);
}
