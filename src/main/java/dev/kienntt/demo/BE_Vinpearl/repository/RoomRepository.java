package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    int deleteById(int id);

    @Query("SELECT r FROM Room r WHERE " +
//            "r.buildingId = :buildingId and r.room_type = :roomType")
            "(:roomType is null or r.roomTypeId = :roomType)")
    Iterable<Room> searchRooms(Long roomType);

    @Query("SELECT new dev.kienntt.demo.BE_Vinpearl.domain.dto.RoomDto(r.id, r.name, r.numberRoom, r.roomTypes.name, r.status) FROM Room r left join BookingRoom br on r.id = br.roomId WHERE " +
            "(:name is null or r.name LIKE CONCAT('%',:name, '%')) and " +
            "(:startDate is null or :endDate is null or (br.checkIn > :endDate or br.checkOut < :startDate)) and " +
            "(:status is null or r.status = :status) and " +
            "(:roomType is null or r.roomTypes.name LIKE CONCAT('%',:roomType, '%'))")
    Page<Room> searchRoomsPage(String name, String roomType, Long status, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query("SELECT r FROM Room r left join BookingRoom br on r.id = br.roomId WHERE " +
            "(:name is null or r.name LIKE CONCAT('%',:name, '%')) and " +
            "(:startDate is null or :endDate is null or ((br.checkIn between :startDate and :endDate) and (br.checkOut between :startDateand and :endDate))) and " +
            "(:status is null or r.status = :status) and " +
            "(:roomType is null or r.roomTypes.name LIKE CONCAT('%',:roomType, '%'))")
    Page<Room> searchRoomsPageAdmin(String name, String roomType, Long status, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query("SELECT r FROM Room r WHERE " +
            "r.status = :status AND r.roomTypeId = :roomTypeId and r.roomGroupType = 0")
    List<Room> findByRoomTypeId(Long roomTypeId, Long status);

    @Query("SELECT r FROM Room r WHERE " +
            "r.roomTypeId = :roomTypeId and r.roomGroupType = 0")
    List<Room> findByRoomTypeId1(Long roomTypeId);

    @Query("SELECT r FROM Room r WHERE " +
            "r.status = :status AND r.roomTypeId = :roomTypeId and r.roomGroupType = 1")
    List<Room> findByRoomGroupType(Long roomTypeId, Long status);

    @Query("SELECT r FROM Room r WHERE " +
            "r.roomTypeId = :roomTypeId and r.roomGroupType = 0 AND r.id NOT IN " +
            "(select b.roomId from BookingRoom b where (b.checkIn between :startDate and :endDate) OR (b.checkOut BETWEEN :startDate AND :endDate))")
//            "(SELECT b.room.id FROM Booking b WHERE (b.checkInDate BETWEEN :startDate AND :endDate) OR (b.checkOutDate BETWEEN :startDate AND :endDate))")
    List<Room> findRoomEmpty(Long roomTypeId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT r FROM Room r WHERE " +
            "r.status = :status AND r.roomTypeId = :roomTypeId and r.roomGroupType = 1 AND r.id NOT IN " +
            "(select b.roomId from BookingRoom b where b.checkIn between :startDate and :endDate OR (b.checkOut BETWEEN :startDate AND :endDate))")
//            "(SELECT b.room.id FROM Booking b WHERE (b.checkInDate BETWEEN :startDate AND :endDate) OR (b.checkOutDate BETWEEN :startDate AND :endDate))")
    List<Room> findRoomTourEmpty(Long roomTypeId, Long status, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT COUNT(r) FROM Room r WHERE r.roomTypeId = :roomTypeId")
    int countRooms(Long roomTypeId);
}
