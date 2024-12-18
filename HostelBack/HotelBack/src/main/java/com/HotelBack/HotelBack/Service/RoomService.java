package com.HotelBack.HotelBack.Service;

import com.HotelBack.HotelBack.DTO.RoomDTO.CreateRoomDTO;
import com.HotelBack.HotelBack.DTO.RoomDTO.ShortRoomDTO;
import com.HotelBack.HotelBack.DTO.RoomDTO.UpdateRoomDTO;
import com.HotelBack.HotelBack.Enities.Room;
import com.HotelBack.HotelBack.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.HotelBack.HotelBack.Enities.Hotel;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<ShortRoomDTO> getRooms() {
        List<Room> rooms = roomRepository.findAll();

        return rooms.stream()
                .map(room -> {
                    ShortRoomDTO roomDTO = new ShortRoomDTO();
                    roomDTO.setRoom_id(room.getId());
                    roomDTO.setHotel_id(room.getHotel().getId());
                    roomDTO.setRoomNumber(room.getRoomNumber());
                    roomDTO.setRoomType(room.getRoomType());
                    roomDTO.setPrice(room.getPrice());
                    roomDTO.setImager(room.getImager());

                    return roomDTO;
                }).toList();
    }

    public ShortRoomDTO getRoomById(int id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Room not found"));

        ShortRoomDTO roomDTO = new ShortRoomDTO();
        roomDTO.setRoom_id(room.getId());
        roomDTO.setHotel_id(room.getHotel().getId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setImager(room.getImager());

        return roomDTO;
    }

    public void createRoom(CreateRoomDTO createRoomDTO) {
        Room room = new Room();
        room.setHotel(new Hotel()); // Предположим, что Hotel уже загружен по ID
        room.getHotel().setId(createRoomDTO.getHotel_id());
        room.setRoomNumber(createRoomDTO.getRoomNumber());
        room.setRoomType(createRoomDTO.getRoomType());
        room.setPrice(createRoomDTO.getPrice());
        room.setImager(createRoomDTO.getImager());

        roomRepository.save(room);
    }

    public void updateRoom(int id, UpdateRoomDTO updateRoomDTO) {
        Room roomToUpdate = roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Room with ID " + id + " not found"));

        if (updateRoomDTO.getHotel_id() != null) {
            roomToUpdate.setHotel(new Hotel());
            roomToUpdate.getHotel().setId(updateRoomDTO.getHotel_id());
        }
        if (updateRoomDTO.getRoomNumber() != null) {
            roomToUpdate.setRoomNumber(updateRoomDTO.getRoomNumber());
        }
        if (updateRoomDTO.getRoomType() != null) {
            roomToUpdate.setRoomType(updateRoomDTO.getRoomType());
        }
        if (updateRoomDTO.getPrice() != null) {
            roomToUpdate.setPrice(updateRoomDTO.getPrice());
        }
        if (updateRoomDTO.getImager() != null) {
            roomToUpdate.setImager(updateRoomDTO.getImager());
        }

        roomRepository.save(roomToUpdate);
    }

    public void deleteRoom(int id) {
        Room roomToDelete = roomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Room with ID " + id + " not found"));

        roomRepository.delete(roomToDelete);
    }
}