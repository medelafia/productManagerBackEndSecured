package com.example.crud_backend.repositry;

import com.example.crud_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositry extends JpaRepository<Product,Integer> {
}
