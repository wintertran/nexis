package com.example.nexis.entity;


import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "`Provinces`")
public class Province {
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

    @Column(name = "`AdministrativeUnitId`")
    private Integer administrativeUnitId;

    @Column(name = "`AdministrativeRegionId`")
    private Integer administrativeRegionId;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAdministrativeRegionId() {
        return administrativeRegionId;
    }

    public void setAdministrativeRegionId(Integer administrativeRegionId) {
        this.administrativeRegionId = administrativeRegionId;
    }

    public Integer getAdministrativeUnitId() {
        return administrativeUnitId;
    }

    public void setAdministrativeUnitId(Integer administrativeUnitId) {
        this.administrativeUnitId = administrativeUnitId;
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
}

