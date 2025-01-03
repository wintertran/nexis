package com.example.nexis.controller;

import com.example.nexis.entity.Address;
import com.example.nexis.entity.Province;
import com.example.nexis.entity.Ward;
import com.example.nexis.repository.AddressRepository;
import com.example.nexis.repository.ProvinceRepository;
import com.example.nexis.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private WardRepository wardRepository;

    // Get all provinces
    @GetMapping("/provinces")
    public ResponseEntity<List<Province>> getAllProvinces() {
        return ResponseEntity.ok(provinceRepository.findAll());
    }

    // Get districts by province ID
    @GetMapping("/districts/{provinceId}")
    public ResponseEntity<List<Ward>> getDistrictsByProvinceId(@PathVariable String provinceId) {
        // Assuming districts are part of Wards (you can replace logic accordingly)
        List<Ward> wards = wardRepository.findByDistrictId(provinceId);
        return ResponseEntity.ok(wards);
    }

    // Get wards by district ID
    @GetMapping("/wards/{districtId}")
    public ResponseEntity<List<Ward>> getWardsByDistrictId(@PathVariable String districtId) {
        List<Ward> wards = wardRepository.findByDistrictId(districtId);
        return ResponseEntity.ok(wards);
    }

    // Get address by ID
    @GetMapping("/get/{addressId}")
    public ResponseEntity<Optional<Address>> getAddressById(@PathVariable String addressId) {
        return ResponseEntity.ok(addressRepository.findById(addressId));
    }

    // Get all addresses
    @GetMapping("/get-all")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressRepository.findAll());
    }

    // Add a new address
    @PostMapping("/add")
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        return ResponseEntity.ok(addressRepository.save(address));
    }

    // Update an address
    @PutMapping("/update/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable String addressId, @RequestBody Address updatedAddress) {
        Optional<Address> existingAddressOpt = addressRepository.findById(addressId);

        if (existingAddressOpt.isPresent()) {
            Address existingAddress = existingAddressOpt.get();
            existingAddress.setStreetAddress(updatedAddress.getStreetAddress());
            existingAddress.setProvinceId(updatedAddress.getProvinceId());
            existingAddress.setDistrictId(updatedAddress.getDistrictId());
            existingAddress.setWardId(updatedAddress.getWardId());
            existingAddress.setDefault(updatedAddress.getDefault());
            return ResponseEntity.ok(addressRepository.save(existingAddress));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an address
    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable String addressId) {
        if (addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
