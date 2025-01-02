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

    @OneToOne
    @JoinColumn(name = "`UserId`", referencedColumnName = "`Id`", nullable = false)
    private User user;

    @Column(name = "`ResetToken`")
    private String resetPasswordToken;

    @Column(name = "`TokenExpiration`")
    private Date tokenExpiration;

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

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
