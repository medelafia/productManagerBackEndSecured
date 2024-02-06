package com.example.crud_backend.security.repositry;

import com.example.crud_backend.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositry extends JpaRepository<User , String> {
    public Optional<User> findByUsername(String username) ;
}
