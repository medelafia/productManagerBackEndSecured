package com.example.crud_backend.security.web;

import com.example.crud_backend.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthRestController {
    @Autowired
    TokenService tokenService ;
    @PostMapping("/token")
    public Map<String,String> getToken(String username , String password){
        return tokenService.generateToken(username , password ) ;
    }
    @PostMapping("/refresh-token")
    public Map<String , String> refreshToken(String refreshToken) {
        return tokenService.refreshToken(refreshToken);
    }
}
