package com.example.nexis.service;

import com.example.nexis.entity.Role;
import com.example.nexis.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByUserId(String userId) {
        return roleRepository.findByUserId(userId);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }
}
