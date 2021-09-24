package com.example.postgresql.Service;

import com.example.postgresql.exception.APIException;
import com.example.postgresql.exception.ResourceNotFoundException;
import com.example.postgresql.model.Product;
import com.example.postgresql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.nio.file.ReadOnlyFileSystemException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity getAllProducts(){
        return ResponseEntity.ok(this.productRepository.findAll());
    }

    public ResponseEntity getProductById( Long productId) throws ResourceNotFoundException{
        return ResponseEntity.ok(this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("not avilable"))) ;
    }

    public Product createProduct(Product product){
        return this.productRepository.save(product);
    }

    public ResponseEntity<Product> updateProduct(Long producctId, Product productDetails) throws ResourceNotFoundException {
        Product product=productRepository.findById(producctId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :" + producctId));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        return ResponseEntity.ok(this.productRepository.save(product));
    }

    public Map<String ,Boolean> deleteProduct(Long producctId) throws ResourceNotFoundException {
        Product product=productRepository.findById(producctId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :" + producctId));
        this.productRepository.delete(product);
        Map<String,Boolean> response =new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
