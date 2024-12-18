package com.HotelBack.HotelBack.Service;

import com.HotelBack.HotelBack.DTO.BookingDTO.CreateBookingDTO;
import com.HotelBack.HotelBack.DTO.BookingDTO.ShortBookingDTO;
import com.HotelBack.HotelBack.DTO.BookingDTO.UpdateBookingDTO;
import com.HotelBack.HotelBack.Enities.Booking;
import com.HotelBack.HotelBack.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.HotelBack.HotelBack.Enities.Customer;
import com.HotelBack.HotelBack.Enities.Room;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<ShortBookingDTO> getBookings() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookings.stream()
                .map(booking -> {
                    ShortBookingDTO bookingDTO = new ShortBookingDTO();
                    bookingDTO.setBooking_id(booking.getId());
                    bookingDTO.setCustomer_id(booking.getCustomer().getId());
                    bookingDTO.setRoom_id(booking.getRoom().getId());
                    bookingDTO.setCheckInDate(booking.getCheckInDate());
                    bookingDTO.setCheckOutDate(booking.getCheckOutDate());

                    return bookingDTO;
                }).toList();
    }

    public List<ShortBookingDTO> getBookingsByCustomerId(int customerId) {
        List<Booking> bookings = bookingRepository.findByCustomerId(customerId);
        return bookings.stream()
                .map(booking -> {
                    ShortBookingDTO bookingDTO = new ShortBookingDTO();
                    bookingDTO.setBooking_id(booking.getId());
                    bookingDTO.setCustomer_id(booking.getCustomer().getId());
                    bookingDTO.setRoom_id(booking.getRoom().getId());
                    bookingDTO.setCheckInDate(booking.getCheckInDate());
                    bookingDTO.setCheckOutDate(booking.getCheckOutDate());
                    return bookingDTO;
                }).toList();
    }

    public ShortBookingDTO getBookingById(int id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Booking not found"));

        ShortBookingDTO bookingDTO = new ShortBookingDTO();
        bookingDTO.setBooking_id(booking.getId());
        bookingDTO.setCustomer_id(booking.getCustomer().getId());
        bookingDTO.setRoom_id(booking.getRoom().getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());

        return bookingDTO;
    }

    public void createBooking(CreateBookingDTO createBookingDTO) {
        Booking booking = new Booking();

        // Установить значения бронирования
        booking.setCustomer(new Customer()); // Предположим, что Customer уже загружен по ID
        booking.getCustomer().setId(createBookingDTO.getCustomer_id());
        booking.setRoom(new Room()); // Предположим, что Room уже загружен по ID
        booking.getRoom().setId(createBookingDTO.getRoom_id());
        booking.setCheckInDate(createBookingDTO.getCheckInDate());
        booking.setCheckOutDate(createBookingDTO.getCheckOutDate());

        bookingRepository.save(booking);
    }

    public void updateBooking(int id, UpdateBookingDTO updateBookingDTO) {
        Booking bookingToUpdate = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Booking with ID " + id + " not found"));

        // Обновляем поля только при наличии новых значений в DTO
        if (updateBookingDTO.getCustomer_id() != null) {
            bookingToUpdate.setCustomer(new Customer()); // Если необходимо, создаем новый объект Customer
            bookingToUpdate.getCustomer().setId(updateBookingDTO.getCustomer_id());
        }
        if (updateBookingDTO.getRoom_id() != null) {
            bookingToUpdate.setRoom(new Room()); // Если необходимо, создаем новый объект Room
            bookingToUpdate.getRoom().setId(updateBookingDTO.getRoom_id());
        }
        if (updateBookingDTO.getCheckInDate() != null) {
            bookingToUpdate.setCheckInDate(updateBookingDTO.getCheckInDate());
        }
        if (updateBookingDTO.getCheckOutDate() != null) {
            bookingToUpdate.setCheckOutDate(updateBookingDTO.getCheckOutDate());
        }

        bookingRepository.save(bookingToUpdate);
    }

    public void deleteBooking(int id) {
        Booking bookingToDelete = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Booking with ID " + id + " not found"));

        bookingRepository.delete(bookingToDelete);
    }

    private Booking convertDTOToBooking(UpdateBookingDTO updateBookingDTO, Booking booking) {
        booking.setCustomer(new Customer());
        booking.getCustomer().setId(updateBookingDTO.getCustomer_id());
        booking.setRoom(new Room());
        booking.getRoom().setId(updateBookingDTO.getRoom_id());
        booking.setCheckInDate(updateBookingDTO.getCheckInDate());
        booking.setCheckOutDate(updateBookingDTO.getCheckOutDate());

        return booking;
    }
}