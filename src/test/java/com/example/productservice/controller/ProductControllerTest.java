//package com.example.productservice.controller;
//
//import com.example.productservice.exceptions.ProductNotFoundException;
//import com.example.productservice.models.Product;
//import com.example.productservice.services.ProductService;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.nio.file.AccessDeniedException;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class ProductControllerTest {
//    @Autowired
//    private ProductController productController;
//    @MockBean
//    private ProductService productService;
//    @Test
//    void getProductById() throws ProductNotFoundException, AccessDeniedException {
//        Product p = productController.getProductById("asd",1L);
//        assertEquals(1L, p.getId());
//    }
//}