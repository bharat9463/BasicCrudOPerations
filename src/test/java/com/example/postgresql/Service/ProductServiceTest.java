package com.example.postgresql.Service;

import com.example.postgresql.model.Product;
import com.example.postgresql.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getAllProducts() {
        Product product=new Product("abcd","efgh");
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        assertEquals(1,productService.getAllProducts().size());
    }


    @Test
    void getProductById() {
        Product product = new Product("WOODEN","Almirah");
        product.setId(5L);

        when(productRepository.findById(5L)).thenReturn(java.util.Optional.of(product));
        assertThat(productService.getProductById(5L)).isEqualTo(product);
    }

    @Test
    void createProduct() {
        Product product = new Product("WOODEN","Almirah");

        Mockito.when(productRepository.save(product)).thenReturn(product);
        assertThat(productService.createProduct(product)).isEqualTo(product);
    }



    @Test
    void deleteProduct() {
        Product product=new Product("csncjdn","cjncj");
        product.setId(5);
        productService.deleteProduct(product.getId());
        verify(productRepository).deleteById(product.getId());
    }
}