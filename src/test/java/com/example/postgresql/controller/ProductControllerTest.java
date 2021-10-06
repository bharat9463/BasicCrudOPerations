package com.example.postgresql.controller;

import com.example.postgresql.Service.ProductService;
import com.example.postgresql.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.aspectj.lang.annotation.Before;
import org.aspectj.weaver.patterns.PerObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.util.Assert.*;

@WebMvcTest(ProductController.class)

class ProductControllerTest {

    @Autowired
    private MockMvc  mockMvc;

    @Autowired
    private WebApplicationContext context;

    ObjectMapper objectMapper=new ObjectMapper();

    @Before("")
    public void setup(){
        mockMvc= webAppContextSetup(context).build();
    }

    @MockBean
    private ProductService productService;

    @Test
    void getAllProductsTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/product").content(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk()).andReturn();
        String resultContext= result.getResponse().getContentAsString();
        Response response=objectMapper.readValue(resultContext,Response.class);
        assertEquals(200, response.getStatus());
    }


    @Test
    void createProductTest() throws Exception {
        Product product =new Product("ccdcdf","cdf");
        String JsonRequest=objectMapper.writeValueAsString(product);
        MvcResult result=mockMvc.perform(post("/product/create").content(JsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String resultContext= result.getResponse().getContentAsString();
        Response response=objectMapper.readValue(resultContext,Response.class);
        assertTrue(response.getStatus() == 200);

    }

    @Test
    void updateProductTest() throws Exception {
        Product product =new Product("ccdcdf","cdf");
        String JsonRequest=objectMapper.writeValueAsString(product);
        MvcResult result=mockMvc.perform(put("/product/update").content(JsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String resultContext= result.getResponse().getContentAsString();
        Response response=objectMapper.readValue(resultContext,Response.class);
        assertTrue(response.getStatus() == 200);



    }

    @Test
    void deleteProduct() throws Exception{
        MvcResult result=mockMvc.perform(delete("/product/delete/{id}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String resultContext= result.getResponse().getContentAsString();
        Response response=objectMapper.readValue(resultContext,Response.class);
        assertTrue(response.getStatus() == 200);
    }
}