package com.HotelBack.HotelBack.DTO.HotelDTO;

import lombok.Data;

@Data
public class CreateHotelDTO {

    private Integer hotel_id;
    private String name;
    private String address;
    private String phone;
    private String imageh;

}
