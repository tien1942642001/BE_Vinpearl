package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.domain.dto.RoomTypeMinDto;
import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RoomTypeRepository extends PagingAndSortingRepository<RoomType, Long> {
    @Query("SELECT rt FROM RoomType rt " +
            "left join Room r on r.roomTypeId = rt.id " +
            "left join Service s on s.roomTypeId = rt.id " +
            "left join BookingRoom br on r.id = br.roomId WHERE " +
            "(:numberPerson is null or rt.numberAdult + rt.numberChildren >= :numberPerson) and " +
            "(:hotelName is null or rt.hotel.name LIKE CONCAT('%',:hotelName, '%')) and" +
            "(:acreage is null or rt.acreage = :acreage) and " +
            "(:startDate is null or :endDate is null or not exists (select 1 from BookingRoom br2 where r.id = br2.roomId and br2.checkIn <= :endDate and br2.checkOut >= :startDate)) and " +
            "(:name is null or r.name LIKE CONCAT('%',:name, '%')) " +
            "group by rt.id")
    Page<RoomType> searchRoomTypesPage(Long numberPerson, String hotelName, Long acreage, String name, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    List<RoomType> findRoomTypeByHotelId(Long hotelId);

    @Query(value = "SELECT MIN(s.price) FROM Service s " +
                    "INNER JOIN RoomType r ON r.id = s.roomTypeId " +
                    "INNER JOIN Hotel h ON h.id = r.hotelId " +
                    "WHERE h.id = :hotelId")
    public Long findMinPriceByRoomTypeName(Long hotelId);

    @Query(value = "select new dev.kienntt.demo.BE_Vinpearl.domain.dto.RoomTypeMinDto(r.id, r.name, min(s.price)) FROM Service s " +
            "INNER JOIN RoomType r ON r.id = s.roomTypeId " +
            "INNER JOIN Hotel h ON h.id = r.hotelId " +
            "WHERE h.id = :hotelId")
    public RoomTypeMinDto findRoomTypeByMinPrice(Long hotelId);

    @Modifying
    @Query("UPDATE RoomType r SET r.remainingOfRooms = :remainingOfRooms WHERE r.id = :id")
    void updateRemainingOfRooms(Long id, Long remainingOfRooms);

//    @Query("select r from RoomType r WHERE r.id = :id")
//    RoomType findByRoomTypeId(Long roomId);
}
