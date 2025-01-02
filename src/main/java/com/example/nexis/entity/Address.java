package com.example.nexis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "`Addresses`")
public class Address {
    @Id
    @Column(name = "`Id`")
    private String id;

    @ManyToOne
    @JoinColumn(name = "`UserId`", referencedColumnName = "`Id`", nullable = false)
    private User user;

    @Column(name = "`StreetAddress`")
    private String streetAddress;

    @Column(name = "`ProvinceId`")
    private String provinceId;

    @Column(name = "`DistrictId`")
    private String districtId;

    @Column(name = "`WardId`")
    private String wardId;

    @Column(name = "`IsDefault`")
    private Boolean isDefault;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

