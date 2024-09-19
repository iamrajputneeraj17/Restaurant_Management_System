package com.example.Restaurant.controllers;


import com.example.Restaurant.DTOs.CategoryDto;
import com.example.Restaurant.DTOs.ProductDto;
import com.example.Restaurant.DTOs.ReservationDto;
import com.example.Restaurant.DTOs.UserDto;
import com.example.Restaurant.entites.Category;
import com.example.Restaurant.services.CategoryServiceImpl;
import com.example.Restaurant.services.CustomerServiceImpl;
import com.example.Restaurant.services.ProductServiceImpl;
import com.example.Restaurant.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CustomerServiceImpl customerService;


    @PostMapping("/create/user")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto)
    {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/create/admin")
    public ResponseEntity<UserDto> addAdmin(@RequestBody UserDto userDto)
    {
        return new ResponseEntity<>(userService.createAdmin(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> user = userService.getAllUsers();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create/category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
    {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategory();
        return ResponseEntity.ok(categoryDtoList);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long id)
    {
        return new ResponseEntity<>(categoryService.upCategory(categoryDto, id), HttpStatus.ACCEPTED);
    }

    // Product Operations
    @PostMapping("/create/product/{categoryId}")
    public ResponseEntity<?> postProduct(@RequestBody ProductDto productDto, @PathVariable Long categoryId)
    {
        ProductDto createProduct = productService.createPro(productDto, categoryId);
        if(createProduct == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        return ResponseEntity.status(HttpStatus.CREATED).body(createProduct);

        //Used ? --> because there are two types of response, if null then return string, else return dto
    }

    @GetMapping("/{categoryId}/getProducts")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@PathVariable Long categoryId )
    {
        List<ProductDto> productDtoList = productService.getProductsByCategroyId(categoryId);
        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{categoryId}/categories/{title}")
    public ResponseEntity<List<ProductDto>> getProductByTitle(@PathVariable Long categoryId,  @PathVariable String title)
    {
        List<ProductDto> productDtoList = productService.getProductByTitle(categoryId, title);
        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/getProductById/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId)
    {
        ProductDto getProduct = productService.findByProductId(productId);
        if(getProduct == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found!");
        return ResponseEntity.ok(getProduct);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> UpdateProduct(@RequestBody ProductDto productDto, @PathVariable Long id)
    {
        ProductDto updateProduct = productService.updateProduct(id, productDto);
        if(updateProduct == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong!");
        return ResponseEntity.ok(updateProduct);
    }


    // GetResrvation By admin
    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDto>> getAllReservations()
    {
        List<ReservationDto> reservationDtos = customerService.getAllReservations();
        if(reservationDtos == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reservationDtos);
    }

    @GetMapping("/reservation/{reservationId}/{status}")
    public ResponseEntity<ReservationDto> changeReservationState(@PathVariable Long reservationId, @PathVariable String status)
    {
        ReservationDto updatedReservation= userService.changeReservationStatus(reservationId, status);
        if(updatedReservation == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedReservation);
    }

}



