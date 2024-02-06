package com.example.crud_backend.dto;

import com.example.crud_backend.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductResponse {
    private int id ;
    private String name ;
    private String category ;
    private String description ;
    private int count ;
    private float price ;
    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .description(product.getDescription())
                .count(product.getCount())
                .price(product.getPrice())
                .build();
    }
}
