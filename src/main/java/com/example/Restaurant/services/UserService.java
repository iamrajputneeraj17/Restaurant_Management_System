package com.example.Restaurant.services;

import com.example.Restaurant.DTOs.ReservationDto;
import com.example.Restaurant.DTOs.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto createAdmin(UserDto userDto);

    List<UserDto> getAllUsers();

    ReservationDto changeReservationStatus(Long reservationId, String status);

//    AdminDto createAdmin()
}
