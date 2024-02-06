package com.example.crud_backend.security.web;

import com.example.crud_backend.security.dto.UserRequest;
import com.example.crud_backend.security.entity.User;
import com.example.crud_backend.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    UserService userService ;
    @PostMapping("/registre/")
    public User addUser(@RequestBody UserRequest userRequest ) {
        return userService.saveUser(userRequest) ;
    }
}
