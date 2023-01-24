package com.ideationstorm.com.ideationstorm.category;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;


    public  Iterable<Category> getAllCategories(){

        return categoryRepository.findAll();
    }

    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category updateCategory(CategoryUpdateRequest request) {


        return categoryRepository.save(Category.builder()
                .id(request.getId())
                .name(request.getName())
                .build());
    }

    public Category
    createCategory(CategoryCreateRequest request) {
        return categoryRepository.save(Category.builder().name(request.getName()).build());
    }
}
