package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingTourRepository extends PagingAndSortingRepository<BookingTour, Long> {
    @Query("SELECT b FROM BookingTour b WHERE b.createdDate BETWEEN :startDate AND :endDate")
    List<BookingTour> findByBookingDateBetween(Date startDate, Date endDate);

    @Query("SELECT b FROM BookingTour b")
    List<BookingTour> findAllBookingTour();

    @Query("SELECT b FROM BookingTour b where b.customerId = :id")
    List<BookingTour> findByCustomerId(Long id);

    @Query("SELECT b FROM BookingRoom b WHERE " +
            "(:customerId is null or b.customerId = :customerId) and " +
            "(:status is null or b.paymentStatus = :status) and " +
            "(:code is null or b.code LIKE CONCAT('%',:code, '%')) and " +
            "(:startTime is null or b.checkIn >= :startTime) and (:endTime is null or b.checkOut <= :endTime)")
    Page<BookingTour> searchBookingTour(Long customerId, String code, Long status, Long startTime, Long endTime, Pageable pageable);

}
