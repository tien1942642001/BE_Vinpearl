package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends PagingAndSortingRepository<RoomType, Long> {
    @Query("SELECT r FROM RoomType r WHERE " +
            "(:acreage is null or r.acreage = :acreage) and " +
            "(:name is null or r.name LIKE CONCAT('%',:name, '%'))")
    Page<RoomType> searchRoomTypesPage(Long acreage, String name, Pageable pageable);

    @Query("SELECT roomType FROM RoomType roomType inner join Hotel h on h.id = roomType.hotelId inner join Room r on r.roomTypeId = roomType.id WHERE " +
            "(:acreage is null or roomType.acreage = :acreage) and " +
            "(:name is null or roomType.name = :name)")
    Page<RoomType> searchRoomTypes(Long acreage, String name, Pageable pageable);

    @Query(value = "SELECT MIN(s.price) FROM Service s " +
                    "INNER JOIN RoomType r ON r.id = s.roomTypeId " +
                    "INNER JOIN Hotel h ON h.id = r.hotelId " +
                    "WHERE h.id = :hotelId")
    public Long findMinPriceByRoomTypeName(Long hotelId);
}
