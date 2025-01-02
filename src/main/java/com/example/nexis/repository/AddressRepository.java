package com.example.nexis.repository;

import com.example.nexis.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, String> {
    // Lấy danh sách địa chỉ theo User ID
    List<Address> findByUserId(String userId);

    // Lấy địa chỉ mặc định
    Address findByUserIdAndIsDefaultTrue(String userId);
}
