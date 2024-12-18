package com.HotelBack.HotelBack.repositories;

import com.HotelBack.HotelBack.Enities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByFirstName(String firstName);

}
