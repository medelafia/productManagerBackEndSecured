package com.example.crud_backend.web;

import com.example.crud_backend.dto.ProductRequest;
import com.example.crud_backend.dto.ProductResponse;
import com.example.crud_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("http://localhost:5173")
public class ProductController {
    @Autowired
    ProductService productServices ;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SCOPE_ROLE_admin')")
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest){
        return productServices.addProduct(productRequest) ;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SCOPE_ROLE_user')")
    public List<ProductResponse> getAllProduct() {
        return productServices.getAll();
    }
    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        productServices.deleteProductById(id);
    }
    @PostMapping("/update/{id}")
    public ProductResponse updateProduct(@RequestBody ProductRequest productRequest , @PathVariable int id ){
        return productServices.updateProduct(productRequest , id ) ;
    }
}
