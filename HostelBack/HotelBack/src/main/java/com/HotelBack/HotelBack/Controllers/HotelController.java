package com.HotelBack.HotelBack.Controllers;

import com.HotelBack.HotelBack.Service.HotelService;
import com.HotelBack.HotelBack.DTO.HotelDTO.CreateHotelDTO;
import com.HotelBack.HotelBack.DTO.HotelDTO.ShortHotelDTO;
import com.HotelBack.HotelBack.DTO.HotelDTO.UpdateHotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity; // Импорт для ResponseEntity
import jakarta.validation.Valid; // Импорт для аннотации Valid
import org.springframework.http.HttpStatus; // Импорт для HttpStatus
import java.util.NoSuchElementException; // Импорт для NoSuchElementException

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<ShortHotelDTO> getAllHotels() {
        return hotelService.getHotels();
    }

    @GetMapping("/{id}")
    public ShortHotelDTO getHotelById(@PathVariable int id) {
        return hotelService.getHotelById(id);
    }

    @PostMapping
    public void createHotel(@RequestBody CreateHotelDTO createHotelDTO) {
        hotelService.createHotel(createHotelDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateHotel(@PathVariable("id") int id, @Valid @RequestBody UpdateHotelDTO updateHotelDTO) {
        try {
            hotelService.updateHotel(id, updateHotelDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable("id") int id) {
        try {
            hotelService.deleteHotel(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}