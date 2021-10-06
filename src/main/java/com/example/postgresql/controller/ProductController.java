package com.example.postgresql.controller;

import com.example.postgresql.Service.ProductService;
import com.example.postgresql.exception.ResourceNotFoundException;
import com.example.postgresql.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //get
    @GetMapping
    public ResponseEntity <?> getAllProducts(){
        return ResponseEntity.ok(this.productService.getAllProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException{
        return ResponseEntity.ok(this.productService.getProductById(productId));
    }
    //postMapping
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        return ResponseEntity.ok(this.productService.createProduct(product));
    }
    //putMapping
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long producctId, @RequestBody Product productDetails) throws ResourceNotFoundException {
        return this.productService.updateProduct(producctId,productDetails);
    }
    //deleteMapping
    @DeleteMapping("/delete/{id}")
    public Map<String ,Boolean> deleteProduct(@PathVariable(value = "id") Long producctId) throws ResourceNotFoundException {
        return this.productService.deleteProduct(producctId);
    }

}
