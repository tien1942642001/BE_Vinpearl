package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h WHERE " +
            "(:area is null or h.area = :area) and " +
            "(:phone is null or h.phone = :phone) and " +
            "(:name is null or h.name = :name)")
    Page<Hotel> searchHotel(Long area, String name, String phone, Pageable pageable);
}
