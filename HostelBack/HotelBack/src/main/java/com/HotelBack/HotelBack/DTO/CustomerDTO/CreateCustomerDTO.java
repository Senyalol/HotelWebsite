package com.HotelBack.HotelBack.DTO.CustomerDTO;

import lombok.Data;

@Data
public class CreateCustomerDTO {

    private Integer customer_id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
