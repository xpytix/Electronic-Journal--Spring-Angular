package com.example.CarRentalApi.school.mapper;

import com.example.CarRentalApi.school.dto.CategoryDto;
import com.example.CarRentalApi.school.model.Category;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    public List<CategoryDto> categoriesToCategoriesDto(List<Category> categories);
}
