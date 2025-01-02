package com.example.nexis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
@Table(name = "`Users`")
public class User {
    @Id
    @Column(name = "`Id`")
    private String id;

    @Column(name = "`Name`")
    private String name;

    @Column(name = "`PhoneNumber`")
    private String phoneNumber;

    @Column(name = "`Email`", unique = true)
    private String email;

    @Column(name = "`Gender`")
    private String gender;

    @Column(name = "`DateOfBirth`")
    private Date dateOfBirth;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

