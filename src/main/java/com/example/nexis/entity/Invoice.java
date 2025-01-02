package com.example.nexis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
@Table(name = "`Invoices`")
public class Invoice {
    @Id
    @Column(name = "`Id`")
    private String id;

    @ManyToOne
    @JoinColumn(name = "`OrderId`", referencedColumnName = "`Id`", nullable = false)
    private Order order;

    @Column(name = "`InvoiceDate`")
    private Date invoiceDate;

    @Column(name = "`TotalAmount`")
    private Double totalAmount;

    @Column(name = "`DueDate`")
    private Date dueDate;

    @Column(name = "`PaymentStatus`")
    private String paymentStatus;

    @Column(name = "`PaymentMethod`")
    private String paymentMethod;

    @Column(name = "`CreatedAt`")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "`UserId`", referencedColumnName = "`Id`", nullable = false) // Khóa ngoại đến bảng Users
    private User user;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

