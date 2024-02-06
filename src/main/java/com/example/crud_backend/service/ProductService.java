package com.example.crud_backend.service;

import com.example.crud_backend.dto.ProductRequest;
import com.example.crud_backend.dto.ProductResponse;
import com.example.crud_backend.model.Product;
import com.example.crud_backend.repositry.ProductRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepositry productRepositry ;

    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .category(productRequest.getCategory())
                .description(productRequest.getDescription())
                .count(productRequest.getCount())
                .price(productRequest.getPrice())
                .build();
        product = productRepositry.save(product);
        return ProductResponse.toProductResponse(product) ;
    }
    public List<ProductResponse> getAll() {
        List<Product> products = productRepositry.findAll();
        return products.stream().map(product -> ProductResponse.toProductResponse(product)).toList();
    }
    public void deleteProductById(int id ) {
        productRepositry.deleteById(id);
    }
    @Transactional
    public ProductResponse updateProduct(ProductRequest productRequest , int id) {
        Product product = productRepositry.findById(id).orElseThrow() ;
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setCount(productRequest.getCount());
        product.setPrice(productRequest.getPrice());
        productRepositry.save(product) ;
        return ProductResponse.toProductResponse(product) ;
    }
}
