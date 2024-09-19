package com.example.Restaurant.entites;


import com.example.Restaurant.DTOs.ReservationDto;
import com.example.Restaurant.Enumeration.ReservationState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableType;

    private String description;

    private Date dateTime;

    private ReservationState reservationState;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public ReservationDto getReservationDto() {
        ReservationDto reservationDto =  new ReservationDto();
        reservationDto.setId(id);
        reservationDto.setReservationState(reservationState);
        reservationDto.setTableType(tableType);
        reservationDto.setDescription(description);
        reservationDto.setDateTime(dateTime);
        reservationDto.setCustomerId(user.getId());
        reservationDto.setCustomerName(user.getName());
        return reservationDto;
    }
}
