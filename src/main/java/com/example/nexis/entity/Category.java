package com.example.nexis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "`Categories`")
public class Category {
    @Id
    @Column(name = "`Id`")
    private String id;

    @Column(name = "`Name`")
    private String name;

    @ManyToOne
    @JoinColumn(name = "`ParentCategoryId`", referencedColumnName = "`Id`")
    private Category parentCategory;

    @Column(name = "`IsAvailable`")
    private Boolean isAvailable;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

