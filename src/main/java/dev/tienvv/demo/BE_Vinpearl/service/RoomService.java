package dev.tienvv.demo.BE_Vinpearl.service;

import dev.tienvv.demo.BE_Vinpearl.model.Room;
import dev.tienvv.demo.BE_Vinpearl.model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Iterable findAll();

    Optional findById(Long id);

    Room save(Room room);

    void deleteRoom(Long id);

    Iterable<Room> searchRoom(Long roomType);

    Page<Room> searchRoomPage(String name, String roomType, Long status, Long startTime, Long endTime, Pageable pageable);

    Page<Room> searchRoomPageAdmin(String name, String roomType, Long status, Long startTime, Long endTime, Pageable pageable);
}
