package com.example.Restaurant.DTOs;

import com.example.Restaurant.Enumeration.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name ;

    private String email;

    private String password;

    private UserRole userRole;
}
