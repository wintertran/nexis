package com.example.nexis.repository;

import com.example.nexis.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    // Additional query methods (if needed) can be added here
}
