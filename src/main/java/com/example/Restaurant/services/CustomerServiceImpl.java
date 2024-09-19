package com.example.Restaurant.services;

import com.example.Restaurant.DTOs.CategoryDto;
import com.example.Restaurant.DTOs.ProductDto;
import com.example.Restaurant.DTOs.ReservationDto;
import com.example.Restaurant.Enumeration.ReservationState;
import com.example.Restaurant.Repositories.CategoryRepository;
import com.example.Restaurant.Repositories.ProductRepository;
import com.example.Restaurant.Repositories.ReservationRepository;
import com.example.Restaurant.Repositories.UserRepository;
import com.example.Restaurant.entites.Category;
import com.example.Restaurant.entites.Product;
import com.example.Restaurant.entites.Reservation;
import com.example.Restaurant.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDTO).collect(Collectors.toList());

    }

    @Override
    public List<CategoryDto> getAllCategoriesByName(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductByCategoryIdAndTitle(Long categoryId, String title) {
        return productRepository.findAllByCategoryIdAndNameContaining(categoryId, title).stream().map(Product::getProductDto).collect(Collectors.toList());

    }

    @Override
    public ReservationDto postReseravtion(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getCustomerId());
        if(optionalUser.isPresent())
        {
            Reservation reservation = new Reservation();
            reservation.setTableType(reservationDto.getTableType());
            reservation.setDateTime(reservationDto.getDateTime());
            reservation.setDescription(reservationDto.getDescription());
            reservation.setUser(optionalUser.get());
            reservation.setReservationState(ReservationState.PENDING);
            Reservation postReservation =  reservationRepository.save(reservation);
            ReservationDto postReservationDto =  new ReservationDto();
            postReservationDto.setId(postReservation.getId());
            return postReservationDto;
        }
        return null;
    }

    @Override
    public List<ReservationDto> getReservationByUser(Long customerId) {
        return reservationRepository.findAllByUserId(customerId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll().stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }
}
