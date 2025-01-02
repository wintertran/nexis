package com.example.nexis.controller;

import com.example.nexis.entity.Address;
import com.example.nexis.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    // Lấy danh sách địa chỉ của người dùng
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAddressesByUser(@PathVariable String userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return ResponseEntity.ok(addresses);
    }

    // Thêm địa chỉ mới
    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody Address address) {
        addressRepository.save(address);
        return ResponseEntity.ok("Address added successfully");
    }

    // Cập nhật địa chỉ
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable String id, @RequestBody Address updatedAddress) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            return ResponseEntity.badRequest().body("Address not found");
        }

        address.setStreetAddress(updatedAddress.getStreetAddress());
        address.setProvinceId(updatedAddress.getProvinceId());
        address.setDistrictId(updatedAddress.getDistrictId());
        address.setWardId(updatedAddress.getWardId());
        address.setDefault(updatedAddress.getDefault());
        addressRepository.save(address);

        return ResponseEntity.ok("Address updated successfully");
    }

    // Xóa địa chỉ
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable String id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            return ResponseEntity.badRequest().body("Address not found");
        }

        addressRepository.delete(address);
        return ResponseEntity.ok("Address deleted successfully");
    }
}

