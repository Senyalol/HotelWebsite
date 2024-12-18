package com.HotelBack.HotelBack.DTO.BookingDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBookingDTO {

    private Integer booking_id;
    private Integer customer_id;
    private Integer room_id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}
