package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingTourRepository extends PagingAndSortingRepository<BookingTour, Long> {
    @Query("SELECT b FROM BookingTour b WHERE b.paymentDate BETWEEN :startDate AND :endDate")
    List<BookingTour> findByBookingDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT b FROM BookingTour b")
    List<BookingTour> findAllBookingTour();

    @Query("SELECT b FROM BookingTour b where b.customerId = :id")
    List<BookingTour> findByCustomerId(Long id);

    @Query("SELECT b FROM BookingTour b WHERE " +
            "(:customerId is null or b.customerId = :customerId) and " +
            "(:status is null or b.paymentStatus = :status) and " +
            "(:code is null or b.code LIKE CONCAT('%',:code, '%')) and " +
            "(:startTime is null or :endTime is null or b.paymentDate between :startTime and :endTime)")
    Page<BookingTour> searchBookingTour(Long customerId, String code, Long status, LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);

    @Query("SELECT b FROM BookingTour b where b.paymentCode = :paymentCode")
    BookingTour findByPaymentCode(String paymentCode);

    @Query("SELECT b FROM BookingTour b where " +
            "(:status is null or b.paymentStatus = :status) and " +
            "(:startDate is null or :endDate is null or b.paymentDate between :startDate and :endDate)")
    List<BookingTour> searchExport(LocalDateTime startDate,LocalDateTime endDate, Long status);
}
