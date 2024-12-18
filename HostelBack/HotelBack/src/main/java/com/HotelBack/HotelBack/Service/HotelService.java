package com.HotelBack.HotelBack.Service;

import com.HotelBack.HotelBack.DTO.HotelDTO.CreateHotelDTO;
import com.HotelBack.HotelBack.DTO.HotelDTO.ShortHotelDTO;
import com.HotelBack.HotelBack.DTO.HotelDTO.UpdateHotelDTO;
import com.HotelBack.HotelBack.Enities.Hotel;
import com.HotelBack.HotelBack.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<ShortHotelDTO> getHotels() {
        List<Hotel> hotels = hotelRepository.findAll();

        return hotels.stream()
                .map(hotel -> {
                    ShortHotelDTO hotelDTO = new ShortHotelDTO();
                    hotelDTO.setHotel_id(hotel.getId());
                    hotelDTO.setName(hotel.getName());
                    hotelDTO.setAddress(hotel.getAddress());
                    hotelDTO.setPhone(hotel.getPhone());
                    hotelDTO.setImageh(hotel.getImageh());

                    return hotelDTO;
                }).toList();
    }

    public ShortHotelDTO getHotelById(int id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hotel not found"));

        ShortHotelDTO hotelDTO = new ShortHotelDTO();
        hotelDTO.setHotel_id(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setPhone(hotel.getPhone());
        hotelDTO.setImageh(hotel.getImageh());

        return hotelDTO;
    }

    public void createHotel(CreateHotelDTO createHotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(createHotelDTO.getName());
        hotel.setAddress(createHotelDTO.getAddress());
        hotel.setPhone(createHotelDTO.getPhone());
        hotel.setImageh(createHotelDTO.getImageh());

        hotelRepository.save(hotel);
    }

    public void updateHotel(int id, UpdateHotelDTO updateHotelDTO) {
        Hotel hotelToUpdate = hotelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hotel with ID " + id + " not found"));

        if (updateHotelDTO.getName() != null) {
            hotelToUpdate.setName(updateHotelDTO.getName());
        }
        if (updateHotelDTO.getAddress() != null) {
            hotelToUpdate.setAddress(updateHotelDTO.getAddress());
        }
        if (updateHotelDTO.getPhone() != null) {
            hotelToUpdate.setPhone(updateHotelDTO.getPhone());
        }
        if (updateHotelDTO.getImageh() != null) {
            hotelToUpdate.setImageh(updateHotelDTO.getImageh());
        }

        hotelRepository.save(hotelToUpdate);
    }

    public void deleteHotel(int id) {
        Hotel hotelToDelete = hotelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hotel with ID " + id + " not found"));

        hotelRepository.delete(hotelToDelete);
    }
}