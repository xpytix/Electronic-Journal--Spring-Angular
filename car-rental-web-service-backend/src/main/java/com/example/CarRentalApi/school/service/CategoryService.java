package com.example.CarRentalApi.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentalApi.school.dto.CategoryDto;
import com.example.CarRentalApi.school.mapper.MapStructMapper;
import com.example.CarRentalApi.school.model.Category;
import com.example.CarRentalApi.school.repository.CategoryRepository;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MapStructMapper mapStructMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, MapStructMapper mapStructMapper) {
        this.categoryRepository = categoryRepository;
        this.mapStructMapper = mapStructMapper;
    }

    public List<CategoryDto> getCategories() {
        return mapStructMapper.categoriesToCategoriesDto(categoryRepository.findAll());
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
