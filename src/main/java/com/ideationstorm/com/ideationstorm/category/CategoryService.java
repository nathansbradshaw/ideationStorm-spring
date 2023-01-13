package com.ideationstorm.com.ideationstorm.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category updateCategory(CategoryUpdateRequest request) {
        return categoryRepository.save(Category.builder()
                .id(request.getId())
                .name(request.getName())
                .build());
    }
}
