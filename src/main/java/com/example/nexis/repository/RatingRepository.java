package com.example.nexis.repository;

import com.example.nexis.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, String> {
    // Lấy đánh giá theo User ID
    List<Rating> findByUserId(String userId);

    // Lấy đánh giá theo Product ID
    List<Rating> findByProductId(String productId);

    // Lấy tất cả đánh giá có giá trị cụ thể
    List<Rating> findByRatingValue(Rating ratingValue);
}
