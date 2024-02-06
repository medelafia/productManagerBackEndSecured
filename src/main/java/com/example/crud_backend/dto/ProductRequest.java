package com.example.crud_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ProductRequest {
    private String name ;
    private String category ;
    private String description ;
    private int count ;
    private float price ;
}
