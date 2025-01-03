package com.example.nexis.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "`Orders`")
public class Order {
        public enum Status {
            PENDING,
            IN_PROGRESS,
            COMPLETED,
            CANCELLED
        }
    @Id
    @Column(name = "`Id`")
    private String id;

    @ManyToOne
    @JoinColumn(name = "`UserId`", referencedColumnName = "`Id`", nullable = false)
    private User user;

    @Column(name = "`ShippingAddressId`")
    private String shippingAddressId;

    @Column(name = "`CartSnapshot`")
    private String cartSnapshot;

    @Column(name = "`CartId`")
    private String cartId;

    @Column(name = "`TotalAmount`")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "`Status`") // Ánh xạ cột Status
    private Status status;

    @Column(name = "`PaymentMethod`")
    private String paymentMethod;

    @Column(name = "`OrderDate`")
    private Date orderDate;

    @Column(name = "`ShippingDate`")
    private Date shippingDate;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartSnapshot() {
        return cartSnapshot;
    }

    public void setCartSnapshot(String cartSnapshot) {
        this.cartSnapshot = cartSnapshot;
    }

    public String getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(String shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

