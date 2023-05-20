package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRoomRepository extends  PagingAndSortingRepository<BookingRoom, Long> {
//    List<BookingRoom> findBookingRoomBy(long departmentId, Pageable pageable);

    @Query("SELECT b FROM BookingRoom b WHERE " +
            "(:customerId is null or b.customerId = :customerId) and " +
            "(:status is null or b.paymentStatus = :status) and " +
            "(:code is null or b.code LIKE CONCAT('%',:code, '%')) and " +
            "(:startDate is null or :endDate is null or (b.paymentDate between :startDate and :endDate))")
    Page<BookingRoom> searchBookingRoomsPage(Long customerId, String code, Long status, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    @Query("SELECT COUNT(b.id) FROM BookingRoom b WHERE b.checkIn between :startMonth and :endMonth")
    Long findAllByMonth(Long startMonth, Long endMonth);

    @Query("SELECT b.customer.fullName, COUNT(b.customer.fullName) AS totalBookings FROM BookingRoom b WHERE b.room.roomTypeId = :roomTypeId GROUP BY b.customer.fullName ORDER BY totalBookings DESC")
    List<Object[]> findTopCustomersByRoomType( Long roomTypeId);

    List<BookingRoom> findByRoomId(Long roomId);

    @Query("SELECT b FROM BookingRoom b")
    List<BookingRoom> findAllBookingRoom();

    @Query("SELECT b FROM BookingRoom b where b.customerId = :id")
    List<BookingRoom> findByCustomerId(Long id);

    @Query("SELECT b FROM BookingRoom b where b.paymentCode = :paymentCode")
    BookingRoom findByPaymentCode(String paymentCode);

    @Query("SELECT b FROM BookingRoom b where " +
            "(:status is null or b.paymentStatus = :status) and " +
            "(:startDate is null or :endDate is null or (b.paymentDate between :startDate and :endDate))")
    List<BookingRoom> searchExport(LocalDateTime startDate,LocalDateTime endDate, Long status);

    @Query("SELECT count(b.id) FROM BookingRoom b where " +
            "(:startTime is null or :endTime is null or (b.paymentDate between :startTime and :endTime))")
    Long countBookingRoom(Long startTime,Long endTime);
}
