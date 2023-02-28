package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    int deleteById(int id);

    @Query("SELECT r FROM Room r WHERE " +
//            "r.buildingId = :buildingId and r.room_type = :roomType")
            "(:roomType is null or r.roomTypeId = :roomType)")
    Iterable<Room> searchRooms(Long roomType);

    @Query("SELECT r FROM Room r WHERE " +
//            "(:hotelId is null or r.roomType.hotelId = :hotelId) and " +
            "(:roomType is null or r.roomTypeId = :roomType)")
    Page<Room> searchRoomsPage(Long roomType, Pageable pageable);

    @Query("SELECT r FROM Room r WHERE " +
            "r.status = :status AND r.roomTypeId = :roomTypeId")
    List<Room> findByRoomTypeId(Long roomTypeId, Integer status);
}
