package com.example.Restaurant.services;

import com.example.Restaurant.DTOs.ProductDto;
import com.example.Restaurant.entites.Product;

import java.util.List;

public interface ProductService {

    ProductDto createPro(ProductDto productDto, Long id);

    List<ProductDto> getProductsByCategroyId(Long id);

    List<ProductDto> getProductByTitle(Long categoryId,  String title);

    ProductDto findByProductId(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto);
}

