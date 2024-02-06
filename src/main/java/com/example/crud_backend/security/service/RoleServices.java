package com.example.crud_backend.security.service;

import com.example.crud_backend.security.entity.Role;
import com.example.crud_backend.security.repositry.RoleRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServices {
    @Autowired
    RoleRepositry roleRepositry ;
    public Role saveRole(String roleName) {
        return roleRepositry.save(Role.builder().roleName(roleName).build());
    }
    public Role findRoleByRoleName(String roleName )  {
        return roleRepositry.findByRoleName(roleName).orElseThrow() ;
    }
}
