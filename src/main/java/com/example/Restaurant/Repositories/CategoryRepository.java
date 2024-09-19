package com.example.Restaurant.Repositories;

import com.example.Restaurant.DTOs.CategoryDto;
import com.example.Restaurant.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByNameContaining(String title);
}
