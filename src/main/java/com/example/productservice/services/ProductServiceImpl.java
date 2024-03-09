package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("SelfProductService")
public class ProductServiceImpl implements ProductService {
    // we create an ImpL class to ensure dependency inversion violation to be
    // fixed otherwise product controller will be dependent on ProductService class(no two concrete classes should depend on each other)
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if(product.isPresent()) {
            Category category = product.get().getCategory();
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product deleteProductById(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if(product.isPresent()) {
            this.productRepository.delete(product.get());
        }
        return product.get();
    }

    @Override
    public Product updateProductById(Long id, Product updatedProduct) {
        Optional<Product> product = this.productRepository.findById(id);
        if(product.isPresent()) {
            product.get().setTitle(updatedProduct.getTitle());
            product.get().setDescription(updatedProduct.getDescription());
            product.get().setPrice(updatedProduct.getPrice());
            product.get().setCategory(updatedProduct.getCategory());
            this.categoryRepository.save(updatedProduct.getCategory());
            this.productRepository.save(product.get());
        }
        return product.get();
    }

    @Override
    public Product addProduct(Product product) {
//        Optional<Category> optionalCategory = categoryRepository.findByName(product.getCategory().getName());
//        if(optionalCategory.isPresent()) {
//            product.setCategory(optionalCategory.get());
//        } else {
//            Category category = categoryRepository.save(product.getCategory());
//            product.setCategory(category);
//        }
        /*The above is replaced by jpa just adding cascading on product, jpa will take care of the above code.*/
        productRepository.save(product);
        return product;
    }
}