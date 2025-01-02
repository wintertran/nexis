package com.example.nexis.controller;

import com.example.nexis.entity.Category;
import com.example.nexis.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Lấy tất cả danh mục
    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    // Lấy danh mục theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable String id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().body("Category not found");
        }
        return ResponseEntity.ok(category);
    }

    // Tạo danh mục mới
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return ResponseEntity.ok("Category created successfully");
    }

    // Cập nhật danh mục
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody Category updatedCategory) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().body("Category not found");
        }

        category.setName(updatedCategory.getName());
        category.setParentCategory(updatedCategory.getParentCategory());
        category.setAvailable(updatedCategory.getAvailable());
        categoryRepository.save(category);

        return ResponseEntity.ok("Category updated successfully");
    }

    // Xóa danh mục
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().body("Category not found");
        }

        categoryRepository.delete(category);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
