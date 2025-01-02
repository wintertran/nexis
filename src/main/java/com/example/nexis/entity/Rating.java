package com.example.nexis.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
@Data
@Entity
@Table(name = "`Ratings`")
public class Rating {
    @Id
    @Column(name = "`Id`")
    private String id;

    @ManyToOne
    @JoinColumn(name = "`UserId`", referencedColumnName = "`Id`", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "`ProductId`", referencedColumnName = "`Id`", nullable = false)
    private Product product;

    @Column(name = "`RatingValue`")
    private Integer ratingValue;

    @Column(name = "`Review`")
    private String review;

    @Column(name = "`CreatedAt`")
    private Date createdAt;

    @Column(name = "`UpdatedAt`")
    private Date updatedAt;
    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
