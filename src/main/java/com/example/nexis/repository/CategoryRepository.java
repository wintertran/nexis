package com.example.nexis.repository;

import com.example.nexis.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
    // Lấy danh sách category con theo parentCategoryId
    Category findByParentCategoryId(String parentCategoryId);
}
