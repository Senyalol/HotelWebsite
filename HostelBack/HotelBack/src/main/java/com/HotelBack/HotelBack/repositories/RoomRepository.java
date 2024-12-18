package com.HotelBack.HotelBack.repositories;

import com.HotelBack.HotelBack.Enities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Room findRoomById(int id);

}
