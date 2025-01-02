package com.example.nexis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "`Roles`") // Tên bảng viết hoa chữ cái đầu
public class Role {
    @Id
    @Column(name = "`Id`") // Tên cột viết hoa chữ cái đầu
    private String id;

    @ManyToOne
    @JoinColumn(name = "`UserId`", nullable = false) // Tên cột viết hoa chữ cái đầu
    private User user;

    @Column(name = "`Role`") // Tên cột viết hoa chữ cái đầu
    private String role;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
