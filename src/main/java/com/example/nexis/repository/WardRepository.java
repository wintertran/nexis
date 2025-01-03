package com.example.nexis.repository;

import com.example.nexis.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, String> {
    List<Ward> findByDistrictId(String districtId);
}
