package com.example.nexis.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "`Wards`")
public class Ward {
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
    @JoinColumn(name = "`DistrictId`", referencedColumnName = "`Id`", nullable = false)
    private District district;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

