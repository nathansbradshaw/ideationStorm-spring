package com.ideationstorm.com.ideationstorm.category;

public class CategoryNotFoundException extends RuntimeException {
    CategoryNotFoundException(Long id) {
        super("could not find category with id " + id);
    }
}
