package com.example.Restaurant.DTOs;


import lombok.Data;

@Data
public class CategoryDto {

    private Long id;

    private String name;
    private String image;
    private String rating;
    private String  description;
}
