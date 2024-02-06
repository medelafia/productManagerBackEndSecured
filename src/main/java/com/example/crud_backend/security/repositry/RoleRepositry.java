package com.example.crud_backend.security.repositry;

import com.example.crud_backend.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositry extends JpaRepository<Role , Long> {
    Optional<Role> findByRoleName(String roleName);
}
