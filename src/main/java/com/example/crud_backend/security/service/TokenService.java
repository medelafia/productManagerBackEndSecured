package com.example.crud_backend.security.service;

import com.example.crud_backend.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TokenService {
    @Autowired
    JwtEncoder jwtEncoder ;
    @Autowired
    AuthenticationManager authenticationManager  ;
    @Autowired
    JwtDecoder jwtDecoder ;
    @Autowired
    UserService userService ;
    public Map<String,String> generateToken(String username , String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Map<String , String > map = new HashMap<>() ;
        Instant instant = Instant.now();
        String subject = authentication.getName() ;
        String scope = authentication.getAuthorities().stream().map(auth-> auth.getAuthority()).collect(Collectors.joining(" "));
        map.put("access-token" , this.generateAccessToken(subject , scope ));
        map.put("refresh-token" , this.generateRefreshToken(subject)) ;
        return map ;
    }
    public Map< String , String > refreshToken(String refreshToken) {
        Map<String,String> map = new HashMap<>() ;
        String subject = jwtDecoder.decode(refreshToken).getSubject();
        User user = userService.findUserByUsername(subject) ;
        if( user == null ) throw new RuntimeException("the token is not valid") ;
        String scope = user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.joining(" "));
        map.put("access-token",this.generateAccessToken(subject,scope));
        map.put("refresh-token" , this.generateRefreshToken(subject)) ;
        return map ;
    }
    private String generateRefreshToken(String subject ) {
        Instant instant = Instant.now() ;
        JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(30 , ChronoUnit.MINUTES))
                .issuer("security-practice")
                .build();
        String  refreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
        return refreshToken ;
    }
    private String generateAccessToken(String subject , String scope) {
        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .expiresAt(instant.plus(5 , ChronoUnit.MINUTES))
                .claim("scope" , scope)
                .issuer("security-practice")
                .build();
        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        return accessToken ;
    }
}
