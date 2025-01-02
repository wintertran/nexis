package com.example.nexis.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Getter
@Setter
@Entity
@Table(name = "`Products`")
public class Product {
    @Id
    @Column(name = "`Id`")
    private String id;

    @ManyToOne
    @JoinColumn(name = "`CategoryId`", referencedColumnName = "`Id`", nullable = false)
    private Category category;

    @Column(name = "`Name`")
    private String name;

    @Column(name = "`Description`")
    private String description;

    @Column(name = "`Price`")
    private Double price;

    @Column(name = "`Sku`")
    private String sku;

    @ManyToOne
    @JoinColumn(name = "`BrandId`", referencedColumnName = "`Id`")
    private Brand brand;

    @Column(name = "`CreatedAt`")
    private Date createdAt;

    @Column(name = "`UpdatedAt`")
    private Date updatedAt;

    @Column(name = "`IsAvailable`")
    private Boolean isAvailable;

    @Column(name = "`StockQuantity`")
    private Double stockQuantity;

    @Column(name = "`CartQuantity`")
    private Double cartQuantity;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Double cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Double stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
