package com.example.postgresql.repository;

import com.example.postgresql.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    //unit test for creating
    @Test
    public void createProductTest(){
        Product product= productRepository.save(new Product("aa","bbb"));

        assertNotNull(product);
        assertThat(product.getId()).isGreaterThan(0);


    }

    @Test
    void updateProductTest(){
        String productName="aaaz";
        Product product=new Product(productName,"cccc");
        product.setId(2);

        productRepository.save(product);
        Product updatedProduct=productRepository.findByName(productName);

        assertThat(updatedProduct.getName()).isEqualTo(productName);

    }
    
    @Test
    public void findAllProductTest(){
        List<Product> products= (List<Product>) productRepository.findAll();
       assertTrue(products.size() >= 0);
    }
    @Test
    public void deleteProductTest(){
        Integer id=2;
        Boolean isExistBeforeDelete=productRepository.findById(Long.valueOf(id)).isPresent();
        productRepository.deleteById(Long.valueOf(id));
        Boolean notExistAfterDelete=productRepository.findById(Long.valueOf(id)).isPresent();
        assertTrue(isExistBeforeDelete);
        assertFalse(notExistAfterDelete);
    }

    @AfterEach
    void tearDown() {

        productRepository.deleteAll();
    }
}