package com.example.productservice.controller;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(@Qualifier("FakeProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    private Product getProductById(@PathVariable Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/products")
    private List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    private Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/products/{id}")
    private Product deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

    @PutMapping("/products/{id}")
    private Product updateProductById(@PathVariable Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProductById(id, product);
    }



    // this violates srp so we create controller advice.
//    @ExceptionHandler(ProductNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND) // we can also use response entity
//    @ResponseBody // output of this method will be written to the HTTP response body
//    private ExceptionDto handleProductNotFoundException(ProductNotFoundException e) {
//        ExceptionDto exceptionDto = new ExceptionDto();
//        exceptionDto.setMessage(e.getMessage());
//        exceptionDto.setStatus("Failure");
//        return exceptionDto;
//    }
}
