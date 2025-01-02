package com.example.nexis.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "`Carts`")
public class Cart {
    @Id
    @Column(name = "`Id`")
    private String id;

    @ManyToOne
    @JoinColumn(name = "`UserId`", referencedColumnName = "`Id`", nullable = false)
    private User user;

    @Column(name = "`Quantity`")
    private Integer quantity;

    @Column(name = "`AddedAt`")
    private Date addedAt;

    @Column(name = "`Status`")
    private String status;

    @Column(name = "`TotalAmount`")
    private Double totalAmount;

    @Column(name = "`IsSavedForLater`")
    private Boolean isSavedForLater;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getSavedForLater() {
        return isSavedForLater;
    }

    public void setSavedForLater(Boolean savedForLater) {
        isSavedForLater = savedForLater;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }
}
