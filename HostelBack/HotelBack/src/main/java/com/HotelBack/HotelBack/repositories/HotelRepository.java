package com.HotelBack.HotelBack.repositories;

import com.HotelBack.HotelBack.Enities.Customer;
import com.HotelBack.HotelBack.Enities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    Hotel findByName(String name);

}
