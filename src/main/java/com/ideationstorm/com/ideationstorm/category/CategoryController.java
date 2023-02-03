package com.ideationstorm.com.ideationstorm.category;

import com.ideationstorm.com.ideationstorm.user.Role;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public  ResponseEntity<Iterable<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        return new ResponseEntity<>( categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Category> getCategoryByName(@RequestParam(value="name") String name) {
        return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
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

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'ROLE_USER')")
    @PutMapping("/update/{id}")
    @Secured({"ROLE_USER", "USER", "ADMIN"})
    public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody String name){
        CategoryUpdateRequest request = CategoryUpdateRequest.builder().id(id).name(name).build();
        return ResponseEntity.ok( categoryService.updateCategory(request));
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
