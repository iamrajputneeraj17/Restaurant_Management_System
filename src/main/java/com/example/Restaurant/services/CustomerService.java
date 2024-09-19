package com.example.Restaurant.services;

import com.example.Restaurant.DTOs.CategoryDto;
import com.example.Restaurant.DTOs.ProductDto;
import com.example.Restaurant.DTOs.ReservationDto;

import java.util.List;

public interface CustomerService {
    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllCategoriesByName(String title);

    List<ProductDto> getProductsByCategoryId(Long categoryId);

    List<ProductDto> getProductByCategoryIdAndTitle(Long categoryId, String title);

    ReservationDto postReseravtion(ReservationDto reservationDto);


    List<ReservationDto> getReservationByUser(Long customerId);

    List<ReservationDto> getAllReservations();

}
