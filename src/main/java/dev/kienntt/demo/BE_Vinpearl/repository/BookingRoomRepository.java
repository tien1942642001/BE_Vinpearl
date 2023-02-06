package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRoomRepository extends  PagingAndSortingRepository<BookingRoom, Long> {
//    List<BookingRoom> findBookingRoomBy(long departmentId, Pageable pageable);

    @Query("SELECT b FROM BookingRoom b WHERE " +
            "(:startTime is null or b.startTime >= :startTime) and (:endTime is null or b.endTime <= :endTime)")
    Page<BookingRoom> searchBookingRoomsPage(Long startTime, Long endTime, Pageable pageable);
}
