package com.example.postgresql.controller;

import com.example.postgresql.Service.ProductService;
import com.example.postgresql.exception.ResourceNotFoundException;
import com.example.postgresql.model.Product;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "This end point is used to fetch all the products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Fetched all the products from the DB",
                    content ={@Content(mediaType = "application/json")}
            ),
            @ApiResponse(responseCode = "400",
            description = "Not available",
            content = @Content)
    })

    @GetMapping
    public ResponseEntity getAllProducts(){
        return this.productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException{
        return this.productService.getProductById(productId) ;
    }
    //postMapping
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product){
        return this.productService.createProduct(product);
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
