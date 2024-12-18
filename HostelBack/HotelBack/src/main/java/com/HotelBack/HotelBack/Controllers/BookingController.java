package com.HotelBack.HotelBack.Controllers;

import com.HotelBack.HotelBack.Service.BookingService;
import com.HotelBack.HotelBack.DTO.BookingDTO.CreateBookingDTO;
import com.HotelBack.HotelBack.DTO.BookingDTO.ShortBookingDTO;
import com.HotelBack.HotelBack.DTO.BookingDTO.UpdateBookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import java.util.NoSuchElementException;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<ShortBookingDTO> getAllBookings() {
        return bookingService.getBookings();
    }

    @GetMapping("/customer/{customerId}")
    public List<ShortBookingDTO> getBookingsByCustomerId(@PathVariable int customerId) {
        return bookingService.getBookingsByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public ShortBookingDTO getBookingById(@PathVariable int id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping
    public void createBooking(@RequestBody CreateBookingDTO createBookingDTO) {
        bookingService.createBooking(createBookingDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBooking(@PathVariable("id") int id, @Valid @RequestBody UpdateBookingDTO updateBookingDTO) {
        try {
            bookingService.updateBooking(id, updateBookingDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") int id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}