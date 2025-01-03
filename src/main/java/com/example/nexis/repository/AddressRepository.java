package com.example.nexis.repository;

import com.example.nexis.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, String> {
    List<Address> findByUserId(String userId); // Get addresses by user
}
