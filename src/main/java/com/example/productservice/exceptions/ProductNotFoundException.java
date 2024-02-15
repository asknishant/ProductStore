package com.example.productservice.exceptions;

import com.example.productservice.models.Product;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " not found");
    }
}
