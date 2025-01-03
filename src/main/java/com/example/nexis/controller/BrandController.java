package com.example.nexis.controller;

import com.example.nexis.entity.Brand;
import com.example.nexis.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    // Get all brands
    @GetMapping("/list")
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return ResponseEntity.ok(brands);
    }
}