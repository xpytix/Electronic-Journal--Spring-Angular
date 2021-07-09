package com.example.CarRentalApi.school.controller;


import com.example.CarRentalApi.school.model.Category;
import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.service.CategoryService;
import com.example.CarRentalApi.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }
    @PostMapping
    public void addNewCategory(@RequestBody Category category)
    {
        categoryService.addNewCategory(category);
    }
    @DeleteMapping(path = "{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }
    @PutMapping
    public void updateCategory( @RequestBody Category category) {
        categoryService.updateCategory(category);
    }

}