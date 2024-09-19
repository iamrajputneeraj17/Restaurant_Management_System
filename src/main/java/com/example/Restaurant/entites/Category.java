package com.example.Restaurant.entites;


import com.example.Restaurant.DTOs.CategoryDto;
import com.example.Restaurant.DTOs.ProductDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private String rating;

    @Column(length = 1000)
    private String  description;


    public CategoryDto getCategoryDTO() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(name);
        categoryDto.setDescription(description);
        categoryDto.setRating(rating);
        categoryDto.setImage(image);
        categoryDto.setId(id);
        return categoryDto;
    }
}
