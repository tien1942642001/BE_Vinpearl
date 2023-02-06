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
            "(:area is null or r.area = :area) and " +
            "(:name is null or r.name = :name)")
    Page<RoomType> searchRoomTypesPage(Long area, String name, Pageable pageable);

    @Query("SELECT roomType FROM RoomType roomType inner join Hotel h on h.id = roomType.hotelId inner join Room r on r.roomTypeId = roomType.id WHERE " +
            "(:area is null or roomType.area = :area) and " +
            "(:name is null or roomType.name = :name)")
    Page<RoomType> searchRoomTypes(Long area, String name, Pageable pageable);
}
