package com.example.crud_backend.security.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class UserRequest {
    private String username ;
    private String email;
    private String firstName ;
    private String lastName ;
    private  String password ;
}
