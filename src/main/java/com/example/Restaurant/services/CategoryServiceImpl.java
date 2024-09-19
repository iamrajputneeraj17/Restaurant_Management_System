package com.example.Restaurant.services;

import com.example.Restaurant.DTOs.CategoryDto;
import com.example.Restaurant.DTOs.ProductDto;
import com.example.Restaurant.Exception.ResourceNotFoundException;
import com.example.Restaurant.Mapping.DtoMapping;
import com.example.Restaurant.Repositories.CategoryRepository;
import com.example.Restaurant.entites.Category;
import com.example.Restaurant.entites.Product;
import com.example.Restaurant.entites.User;
import jakarta.persistence.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DtoMapping dtoMapping;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category =dtoMapping.categoryDtoTocategory(categoryDto);
        Category createdCategory = categoryRepository.save(category);
        return dtoMapping.categoryToCategoryDTo(createdCategory);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((user -> dtoMapping.categoryToCategoryDTo(user)))
                .collect(Collectors.toList());
    }



//    public Employee update(Employee emp, Integer id)
//    {
//        Employee em = repo.findById(id).orElseThrow();
//        em.setName(emp.getName());
//        em.setCompany(emp.getCompany());
//        em.setAddress(emp.getAddress());
//        return repo.save(em);
//
//
//    }

    @Override
    public CategoryDto upCategory(CategoryDto categoryDto, Long id) {
        // Fetch the existing category from the database
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Category Found!" + id));
        existingCategory.setName(categoryDto.getName());
        existingCategory.setImage(categoryDto.getImage());
        existingCategory.setRating(categoryDto.getRating());
        existingCategory.setDescription(categoryDto.getDescription());

        // Save the updated category
        Category updatedCategory = categoryRepository.save(existingCategory);

        // Convert the updated entity back to DTO
        return dtoMapping.categoryToCategoryDTo(updatedCategory);
    }

}
