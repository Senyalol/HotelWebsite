package com.HotelBack.HotelBack.Controllers;

import com.HotelBack.HotelBack.Service.CustomerService;
import com.HotelBack.HotelBack.DTO.CustomerDTO.CreateCustomerDTO;
import com.HotelBack.HotelBack.DTO.CustomerDTO.ShortCustomerInfoDTO;
import com.HotelBack.HotelBack.DTO.CustomerDTO.UpdateCustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity; // Импорт для ResponseEntity
import jakarta.validation.Valid; // Импорт для аннотации Valid
import org.springframework.http.HttpStatus; // Импорт для HttpStatus
import java.util.NoSuchElementException; // Импорт для NoSuchElementException

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<ShortCustomerInfoDTO> getAllCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public ShortCustomerInfoDTO getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public void createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        customerService.createCustomer(createCustomerDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") int id, @Valid @RequestBody UpdateCustomerDTO updateCustomerDTO) {
        try {
            customerService.updateCustomer(id, updateCustomerDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}