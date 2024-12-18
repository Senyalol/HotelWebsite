package com.HotelBack.HotelBack.repositories;

import com.HotelBack.HotelBack.Enities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBookingById(int id);
    List<Booking> findByCustomerId(int customerId); // Новый метод
}