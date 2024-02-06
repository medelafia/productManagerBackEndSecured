package com.example.crud_backend.security.entity;

import com.example.crud_backend.security.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class User {
    @Id
    private String id ;
    @Column(unique = true)
    private String username ;
    private String email;
    private String firstName ;
    private String lastName ;
    @JsonIgnore
    private  String password ;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER )
    private List<Role> roles = new ArrayList<>();
}
