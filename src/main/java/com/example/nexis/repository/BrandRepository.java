package com.example.nexis.repository;

import com.example.nexis.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
    // Additional query methods (if needed) can be added here
}
