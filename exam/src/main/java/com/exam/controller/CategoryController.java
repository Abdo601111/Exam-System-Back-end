package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        Category category1= categoryService.addCategory(category);
        return  ResponseEntity.ok(category1);
    }

    @GetMapping("/{cId}")
    public Category getCategory(@PathVariable("cId") Long cId){
        return categoryService.findCategoryById(cId);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategories( Category category){
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PutMapping("/")
    public ResponseEntity<?> updateCategory(@RequestBody Category category){
        Category category1= categoryService.addCategory(category);
        return  ResponseEntity.ok(category1);
    }

    @DeleteMapping("/{cId}")
    public void deleteCategory(@PathVariable("cId") Long cId){

         categoryService.deleteCategory(cId);
    }
}
