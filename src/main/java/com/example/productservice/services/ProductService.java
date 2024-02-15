package com.example.productservice.services;


import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product deleteProductById(Long id);
    void updateProductById(Long id);
    Product addProduct(Product product);
}