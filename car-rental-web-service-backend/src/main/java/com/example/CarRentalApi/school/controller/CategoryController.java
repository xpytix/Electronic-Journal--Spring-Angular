package com.example.CarRentalApi.school.controller;

import com.example.CarRentalApi.school.model.Category;
import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.service.CategoryService;
import com.example.CarRentalApi.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categoryList = categoryService.getCategories();
        if (categoryList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(categoryList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryList);
    }

    @PostMapping
    public ResponseEntity addNewCategory(@RequestBody Category category) {
        categoryService.addNewCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).header("Info", "Category has been created!").build();
    }

    @DeleteMapping(path = "{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Category has been deleted!").build();
    }

    @PutMapping
    public ResponseEntity updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
        return ResponseEntity.status(HttpStatus.OK).header("Info", "Category has been updated!").build();
    }

}
