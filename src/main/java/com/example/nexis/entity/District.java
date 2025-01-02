package com.example.nexis.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "`Districts`")
public class District {
    @Id
    @Column(name = "`Id`")
    private String id;

    @Column(name = "`Name`")
    private String name;

    @Column(name = "`NameEn`")
    private String nameEn;

    @Column(name = "`FullName`")
    private String fullName;

    @Column(name = "`FullNameEn`")
    private String fullNameEn;

    @Column(name = "`CodeName`")
    private String codeName;

    @ManyToOne
    @JoinColumn(name = "`ProvinceId`", referencedColumnName = "`Id`", nullable = false)
    private Province province;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
}

