package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRoomRepository extends  PagingAndSortingRepository<BookingRoom, Long> {
//    List<BookingRoom> findBookingRoomBy(long departmentId, Pageable pageable);

    @Query("SELECT b FROM BookingRoom b WHERE " +
            "(:startTime is null or b.checkIn >= :startTime) and (:endTime is null or b.checkOut <= :endTime)")
    Page<BookingRoom> searchBookingRoomsPage(Long startTime, Long endTime, Pageable pageable);

    @Query("SELECT COUNT(b.id) FROM BookingRoom b WHERE b.checkIn between :startMonth and :endMonth")
    Long findAllByMonth(Long startMonth, Long endMonth);

    @Query("SELECT b.customer.fullName, COUNT(b.customer.fullName) AS totalBookings FROM BookingRoom b WHERE b.room.roomTypeId = :roomTypeId GROUP BY b.customer.fullName ORDER BY totalBookings DESC")
    List<Object[]> findTopCustomersByRoomType( Long roomTypeId);

    List<BookingRoom> findByRoomId(Long roomId);

    @Query("SELECT b FROM BookingRoom b")
    List<BookingRoom> findAllBookingRoom();

    @Query("SELECT b FROM BookingRoom b where b.customerId = :id")
    List<BookingRoom> findByCustomerId(Long id);
}
