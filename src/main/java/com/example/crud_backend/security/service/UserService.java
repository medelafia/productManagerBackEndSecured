package com.example.crud_backend.security.service;

import com.example.crud_backend.security.dto.UserRequest;
import com.example.crud_backend.security.entity.Role;
import com.example.crud_backend.security.entity.User;
import com.example.crud_backend.security.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepositry userRepositry ;
    @Autowired
    PasswordEncoder passwordEncoder ;
    @Autowired
    RoleServices roleServices ;
    public User saveUser(UserRequest userRequest) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .build();
        return userRepositry.save(user);
    }
    public User findUserById(String id) {
        return userRepositry.findById(id).orElseThrow();
    }
    public User findUserByUsername(String username) {
        return userRepositry.findByUsername(username).orElseThrow() ;
    }
    public List<User> findAllUsers() {
        return userRepositry.findAll();
    }
    public User addRoleToUser(String roleName , String username) {
        User user = this.findUserByUsername(username) ;
        Role role = roleServices.findRoleByRoleName(roleName) ;
        if(role == null || user == null ) throw new RuntimeException("the role or user not exist") ;
        user.getRoles().add(role);
        return userRepositry.save(user) ;
    }
    public User removeRoleFromUser(String roleName , String username ) {
        User user = this.findUserByUsername(username) ;
        Role role = roleServices.findRoleByRoleName(roleName) ;
        if(role == null || user == null ) throw new RuntimeException("the role or user not exist") ;
        user.getRoles().remove(role);
        return userRepositry.save(user) ;
    }
    public void deleteByUsername(String username) {
        userRepositry.delete(this.findUserByUsername(username));
    }
}
