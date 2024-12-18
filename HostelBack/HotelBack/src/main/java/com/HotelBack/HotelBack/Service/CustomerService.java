package com.HotelBack.HotelBack.Service;

import com.HotelBack.HotelBack.DTO.CustomerDTO.CreateCustomerDTO;
import com.HotelBack.HotelBack.DTO.CustomerDTO.ShortCustomerInfoDTO;
import com.HotelBack.HotelBack.DTO.CustomerDTO.UpdateCustomerDTO;
import com.HotelBack.HotelBack.Enities.Customer;
import com.HotelBack.HotelBack.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<ShortCustomerInfoDTO> getCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(customer -> {
                    ShortCustomerInfoDTO customerDTO = new ShortCustomerInfoDTO();
                    customerDTO.setCustomer_id(customer.getId());
                    customerDTO.setFirstName(customer.getFirstName());
                    customerDTO.setLastName(customer.getLastName());
                    customerDTO.setEmail(customer.getEmail());
                    customerDTO.setPhone(customer.getPhone());
                    customerDTO.setPassword(customer.getPassword());

                    return customerDTO;
                }).toList();
    }

    public ShortCustomerInfoDTO getCustomerById(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        ShortCustomerInfoDTO customerDTO = new ShortCustomerInfoDTO();
        customerDTO.setCustomer_id(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setPassword(customer.getPassword());

        return customerDTO;
    }

    public void createCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(createCustomerDTO.getFirstName());
        customer.setLastName(createCustomerDTO.getLastName());
        customer.setEmail(createCustomerDTO.getEmail());
        customer.setPhone(createCustomerDTO.getPhone());
        customer.setPassword(createCustomerDTO.getPassword());

        customerRepository.save(customer);
    }

    public void updateCustomer(int id, UpdateCustomerDTO updateCustomerDTO) {
        Customer customerToUpdate = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer with ID " + id + " not found"));

        if (updateCustomerDTO.getFirstName() != null) {
            customerToUpdate.setFirstName(updateCustomerDTO.getFirstName());
        }
        if (updateCustomerDTO.getLastName() != null) {
            customerToUpdate.setLastName(updateCustomerDTO.getLastName());
        }
        if (updateCustomerDTO.getEmail() != null) {
            customerToUpdate.setEmail(updateCustomerDTO.getEmail());
        }
        if (updateCustomerDTO.getPhone() != null) {
            customerToUpdate.setPhone(updateCustomerDTO.getPhone());
        }
        if(updateCustomerDTO.getPassword() != null) {
            customerToUpdate.setPassword(updateCustomerDTO.getPassword());
        }

        customerRepository.save(customerToUpdate);
    }

    public void deleteCustomer(int id) {
        Customer customerToDelete = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer with ID " + id + " not found"));

        customerRepository.delete(customerToDelete);
    }
}