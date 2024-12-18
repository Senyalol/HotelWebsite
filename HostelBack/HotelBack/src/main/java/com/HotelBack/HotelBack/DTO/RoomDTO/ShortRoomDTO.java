package com.HotelBack.HotelBack.DTO.RoomDTO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ShortRoomDTO {

    private Integer room_id;
    private Integer hotel_id;
    private String roomNumber;
    private String roomType;
    private BigDecimal price;
    private String imager;

}
