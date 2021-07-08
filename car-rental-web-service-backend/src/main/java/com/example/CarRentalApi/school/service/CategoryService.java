package com.example.CarRentalApi.school.service;

import com.example.CarRentalApi.school.model.Category;
import com.example.CarRentalApi.school.model.Student;
import com.example.CarRentalApi.school.repository.CategoryRepository;
import com.example.CarRentalApi.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getCategories() {
        return categoryRepository.findAll();

    }

    public void addNewCategory(Category category) {
        categoryRepository.save(category);

    }

    public void deleteCategory(Long categoryId) {
        boolean exist = categoryRepository.existsById(categoryId);
        if (!exist) {
            throw new IllegalStateException(
                    "category with id " + categoryId + "does not exist");
        }
        categoryRepository.deleteById(categoryId);
    }


    public void updateCategory(Category category) {
        Optional<Category> exist = categoryRepository.findById(category.getId());

        Category categoryToUpdate = exist.orElseThrow(() -> new IllegalStateException(
                "category with id " + category.getId() + "does not exist")
        );

        categoryToUpdate.setName(category.getName());


        categoryRepository.save(categoryToUpdate);

    }

}