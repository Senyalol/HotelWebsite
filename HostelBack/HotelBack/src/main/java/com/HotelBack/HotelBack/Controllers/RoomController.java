package com.HotelBack.HotelBack.Controllers;

import com.HotelBack.HotelBack.Service.RoomService;
import com.HotelBack.HotelBack.DTO.RoomDTO.CreateRoomDTO;
import com.HotelBack.HotelBack.DTO.RoomDTO.ShortRoomDTO;
import com.HotelBack.HotelBack.DTO.RoomDTO.UpdateRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity; // Импорт для ResponseEntity
import jakarta.validation.Valid; // Импорт для аннотации Valid
import org.springframework.http.HttpStatus; // Импорт для HttpStatus
import java.util.NoSuchElementException; // Импорт для NoSuchElementException

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<ShortRoomDTO> getAllRooms() {
        return roomService.getRooms();
    }

    @GetMapping("/{id}")
    public ShortRoomDTO getRoomById(@PathVariable int id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public void createRoom(@RequestBody CreateRoomDTO createRoomDTO) {
        roomService.createRoom(createRoomDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable("id") int id, @Valid @RequestBody UpdateRoomDTO updateRoomDTO) {
        try {
            roomService.updateRoom(id, updateRoomDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("id") int id) {
        try {
            roomService.deleteRoom(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Логируйте ошибку
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}