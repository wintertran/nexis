package com.example.nexis.controller;

import com.example.nexis.entity.Rating;
import com.example.nexis.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    // Lấy tất cả đánh giá cho một sản phẩm
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getRatingsByProduct(@PathVariable String productId) {
        List<Rating> ratings = ratingRepository.findByProductId(productId);
        return ResponseEntity.ok(ratings);
    }

    // Thêm đánh giá mới cho sản phẩm
    @PostMapping
    public ResponseEntity<?> addRating(@RequestBody Rating rating) {
        ratingRepository.save(rating);
        return ResponseEntity.ok("Rating added successfully");
    }

    // Xóa đánh giá
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable String id) {
        Rating rating = ratingRepository.findById(id).orElse(null);
        if (rating == null) {
            return ResponseEntity.badRequest().body("Rating not found");
        }

        ratingRepository.delete(rating);
        return ResponseEntity.ok("Rating deleted successfully");
    }
}
