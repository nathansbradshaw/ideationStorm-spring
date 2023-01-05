package com.ideationstorm.com.ideationstorm.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("category")
    public  @ResponseBody Iterable<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("category/{id}")
    public @ResponseBody CategoryEntity getCategoryByName(@PathVariable("id") Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @PostMapping("category")
    public  ResponseEntity<CategoryEntity> createCategory(@RequestBody String name){
        try {
            CategoryEntity _category = categoryRepository.save(new CategoryEntity(name));
            return new ResponseEntity<>(_category, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
