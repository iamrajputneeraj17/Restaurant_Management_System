package com.example.Restaurant.DTOs;

import com.example.Restaurant.Enumeration.ReservationState;
import lombok.Data;

import java.util.Date;


@Data
public class ReservationDto {

    private Long id;

    private String tableType;

    private String description;

    private Date dateTime;

    private ReservationState reservationState;

    private Long customerId;

    private String customerName;
}
