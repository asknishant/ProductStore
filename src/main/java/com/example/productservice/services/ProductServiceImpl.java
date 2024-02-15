package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductService")
public class ProductServiceImpl implements ProductService {
    // we create an ImpL class to ensure dependency inversion violation to be
    // fixed otherwise product controller will be dependent on ProductService class(no two concrete classes should depend on each other)
    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product deleteProductById(Long id) {
        return null;
    }

    @Override
    public void updateProductById(Long id) {

    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }
}