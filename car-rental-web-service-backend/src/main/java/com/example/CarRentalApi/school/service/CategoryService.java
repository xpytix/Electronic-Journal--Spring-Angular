package com.example.CarRentalApi.school.service;

import java.util.List;
import java.util.Optional;

import com.example.CarRentalApi.school.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApi.school.dto.category.CategoryDto;
import com.example.CarRentalApi.school.model.Category;
import com.example.CarRentalApi.school.repository.CategoryRepository;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getCategories() {
        return categoryMapper.categoriesToCategoriesDto(categoryRepository.findAll());
    }

    public void addNewCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        boolean exist = categoryRepository.existsById(categoryId);
        if (!exist) {
            throw new IllegalStateException("category with id " + categoryId + "does not exist");
        }
        categoryRepository.deleteById(categoryId);
    }

    public void updateCategory(Category category) {
        Optional<Category> exist = categoryRepository.findById(category.getId());

        Category categoryToUpdate = exist.orElseThrow(
                () -> new IllegalStateException("category with id " + category.getId() + "does not exist"));
        categoryToUpdate.setName(category.getName());
        categoryRepository.save(categoryToUpdate);
    }

}
