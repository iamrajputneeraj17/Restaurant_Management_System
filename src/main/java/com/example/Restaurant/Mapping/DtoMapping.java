package com.example.Restaurant.Mapping;

import com.example.Restaurant.DTOs.CategoryDto;
import com.example.Restaurant.DTOs.ProductDto;
import com.example.Restaurant.DTOs.UserDto;
import com.example.Restaurant.Enumeration.UserRole;
import com.example.Restaurant.entites.Category;
import com.example.Restaurant.entites.Product;
import com.example.Restaurant.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoMapping {



    public UserDto userToUserDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setUserRole(user.getUserRole());
        userDto.setId(user.getId());

        return userDto;
    }

    public User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserRole(UserRole.CUSTOMER);
        user.setName(userDto.getName());
        return user;
    }

    public User adminDtoToUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserRole(UserRole.ADMIN);
        user.setName(userDto.getName());
        return user;
    }

    public Category categoryDtoTocategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setRating(categoryDto.getRating());
        category.setImage(categoryDto.getImage());
        return category;
    }

    public CategoryDto categoryToCategoryDTo(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setRating(category.getRating());
        categoryDto.setImage(category.getImage());
        categoryDto.setId(category.getId());
        return categoryDto;
    }

//    public ProductDto getProductDto() {
//        ProductDto productDto = new ProductDto();
//        productDto.setName(product.getName());
//        productDto.setDescription(product.getDescription());
//        productDto.setPrice(product.getPrice());
//        productDto.setImg(product.getImg());
//        productDto.setId(product.getId());
//        productDto.setCategoryId(category.getId());
//        productDto.setCategoryName(category.getName());
//        return productDto;
//    }

}

