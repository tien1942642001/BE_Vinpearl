package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookingTourRepository extends PagingAndSortingRepository<BookingTour, Long> {
    @Query("SELECT b FROM BookingTour b WHERE b.createdDate BETWEEN :startDate AND :endDate")
    List<BookingTour> findByBookingDateBetween(Date startDate, Date endDate);

    @Query("SELECT b FROM BookingTour b")
    List<BookingTour> findAllBookingTour();

    @Query("SELECT b FROM BookingTour b where b.customerId = :id")
    List<BookingTour> findByCustomerId(Long id);
}
