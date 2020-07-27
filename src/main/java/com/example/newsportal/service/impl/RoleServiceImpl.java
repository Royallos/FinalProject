package com.example.newsportal.service.impl;

import com.example.newsportal.model.Role;
import com.example.newsportal.model.User;
import com.example.newsportal.repository.RoleRepository;
import com.example.newsportal.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void setUserRole(User user) {
        Role role = roleRepository.findById(1l).get();
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
    }
}
