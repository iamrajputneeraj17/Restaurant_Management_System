package com.example.Restaurant.services;

import com.example.Restaurant.DTOs.CategoryDto;
import com.example.Restaurant.DTOs.ProductDto;
import com.example.Restaurant.entites.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto upCategory(CategoryDto categoryName, Long id);


    List<CategoryDto> getAllCategory();
}
