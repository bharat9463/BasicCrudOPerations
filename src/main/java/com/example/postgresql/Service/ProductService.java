package com.example.postgresql.Service;

import com.example.postgresql.exception.APIException;
import com.example.postgresql.exception.ResourceNotFoundException;
import com.example.postgresql.exception.UnableToSaveException;
import com.example.postgresql.model.Product;
import com.example.postgresql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.*;

@Component
public class ProductService {

    private static final Logger LOGGER=Logger.getLogger(ProductService.class.getName());
    static {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(ProductService.class.getSimpleName() + ".log");
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.addHandler(fileHandler);
    }

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts(){
        LOGGER.log(Level.INFO,"Getting all the products");
        return this.productRepository.findAll();
    }

    public Product getProductById( Long productId) throws ResourceNotFoundException{
        LOGGER.log(Level.INFO,"Fetching product related to its id");
        return (this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("not avilable"))) ;
    }

    public Product createProduct(Product product) {
        LOGGER.log(Level.INFO,"Creating a product");
        try{
           return (this.productRepository.save(product));
        }
        catch(UnableToSaveException | IllegalArgumentException exception){
            throw exception;
        }
    }

    public ResponseEntity<Product> updateProduct(Long producctId, Product productDetails) throws ResourceNotFoundException {
        Product product=productRepository.findById(producctId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :" + producctId));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        LOGGER.log(Level.INFO,"updating a product");
        try{
            return ResponseEntity.ok(this.productRepository.save(product));
        }
        catch(UnableToSaveException | IllegalArgumentException exception){
            throw exception;
        }
    }

    public Map<String ,Boolean> deleteProduct(Long producctId) throws ResourceNotFoundException {
        Product product=productRepository.findById(producctId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :" + producctId));
        try{
            this.productRepository.delete(product);
        }
        catch(IllegalArgumentException exception){
            throw exception;
        }
        Map<String,Boolean> response =new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        LOGGER.log(Level.INFO,"deleting a product");
        return response;
    }
}
