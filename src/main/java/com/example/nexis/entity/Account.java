package com.example.nexis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "`Accounts`") // Tên bảng với dấu backtick
public class Account {
    @Id
    @Column(name = "`Id`") // Tên cột với dấu backtick
    private String id;
    @Column(name = "`Username`", nullable = false, unique = true)
    private String username;

    @Column(name = "`PasswordHash`", nullable = false)
    private String passwordHash;

    @Column(name = "`UserId`")
    private String userId;

    @Column(name = "`ResetToken`")
    private String resetToken;

    @Column(name = "`TokenExpiration`")
    private Date tokenExpiration;

    public Account() {}

    public Account(String id, String userId, String username, String passwordHash, String resetToken) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.resetToken = resetToken;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(Date tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
