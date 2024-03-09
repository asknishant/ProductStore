package com.example.productservice.controller;

import com.example.productservice.models.Category;
import com.example.productservice.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
    private CategoryRepository categoryRepository; // just to get
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories/{id}")
    public Category getCategories(@PathVariable("id") Long id) {
        return categoryRepository.findById(id).get();
    }
}
