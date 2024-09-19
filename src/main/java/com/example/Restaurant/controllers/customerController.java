package com.example.Restaurant.controllers;


import com.example.Restaurant.DTOs.CategoryDto;
import com.example.Restaurant.DTOs.ProductDto;
import com.example.Restaurant.DTOs.ReservationDto;
import com.example.Restaurant.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class customerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        List<CategoryDto> categoryDtoList = service.getAllCategories();
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/categoriesByName/{title}")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByName(@PathVariable String title)
    {
        List<CategoryDto> categoryDtoList = service.getAllCategoriesByName(title);
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable Long categoryId)
    {
        List<ProductDto> productDtoList = service.getProductsByCategoryId(categoryId);
        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{categoryId}/categories/{title}")
    public ResponseEntity<List<ProductDto>> getProductByCategoryAndTitle(@PathVariable Long categoryId,  @PathVariable String title)
    {
        List<ProductDto> productDtoList = service.getProductByCategoryIdAndTitle(categoryId, title);
        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @PostMapping("/create/reservation")
    public ResponseEntity<?> postReseravtion(@RequestBody ReservationDto reservationDto)
    {
        ReservationDto createdDto =  service.postReseravtion(reservationDto);
        if(createdDto == null) return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    git add .
    git add .


}
