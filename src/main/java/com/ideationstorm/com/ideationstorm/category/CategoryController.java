package com.ideationstorm.com.ideationstorm.category;

import com.ideationstorm.com.ideationstorm.user.Role;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public  @ResponseBody Iterable<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public @ResponseBody Category getCategoryByName(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/create")
    public  ResponseEntity<Category> createCategory(@RequestBody CategoryCreateRequest request){
        try {
            Category category = categoryService.createCategory(request);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryUpdateRequest categoryUpdateRequest){
        return ResponseEntity.ok( categoryService.updateCategory(categoryUpdateRequest));
    }

    ///TODO split this into service
//    @PostMapping("/{id}/delete")
//    @RolesAllowed("ADMIN")
//    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id){
//        try {
//            Category categoryToDelete = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
//            categoryRepository.delete(categoryToDelete);
//            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


}
