package com.example.Restaurant.services;

import com.example.Restaurant.DTOs.ReservationDto;
import com.example.Restaurant.DTOs.UserDto;
import com.example.Restaurant.Enumeration.ReservationState;
import com.example.Restaurant.Mapping.DtoMapping;
import com.example.Restaurant.Repositories.ReservationRepository;
import com.example.Restaurant.Repositories.UserRepository;
import com.example.Restaurant.entites.Reservation;
import com.example.Restaurant.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private DtoMapping dtoMapping;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoMapping.userDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return dtoMapping.userToUserDto(savedUser);
    }

    @Override
    public UserDto createAdmin(UserDto userDto) {
        User admin = dtoMapping.adminDtoToUser(userDto);
        User savedAdmin = userRepository.save(admin);
        return dtoMapping.userToUserDto(savedAdmin);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user -> dtoMapping.userToUserDto(user)))
                .collect(Collectors.toList());
    }


    //Admin access
    @Override
    public ReservationDto changeReservationStatus(Long reservationId, String status) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if(optionalReservation.isPresent())
        {
            Reservation existingreservation = optionalReservation.get();
            if(Objects.equals(status, "Approve"))
            {
                existingreservation.setReservationState(ReservationState.APPROVED);
            }
            else
            {
                existingreservation.setReservationState(ReservationState.DISAPPROVED);
            }
            Reservation reservation = reservationRepository.save(existingreservation);
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setId(reservation.getId());
            return reservationDto;
        }
        return null;
    }
}
