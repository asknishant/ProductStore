package com.example.productservice.controller;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.security.services.AuthenticatioinService;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private ProductService productService;
    private AuthenticatioinService authenticatioinService;
    @Autowired
    public ProductController(@Qualifier("SelfProductService") ProductService productService, AuthenticatioinService authenticatioinService) {
        this.productService = productService;
        this.authenticatioinService = authenticatioinService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@RequestHeader("AuthenticationToken") String token, @PathVariable Long id) throws ProductNotFoundException, AccessDeniedException {
        if(authenticatioinService.authenticate(token) == false) {
            throw new AccessDeniedException("You are not authoriazed to access this resource.");
        }
        return productService.getProductById(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

    @PutMapping("/products/{id}")
    public Product updateProductById(@PathVariable Long id, @RequestBody Product product) throws ProductNotFoundException {
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
