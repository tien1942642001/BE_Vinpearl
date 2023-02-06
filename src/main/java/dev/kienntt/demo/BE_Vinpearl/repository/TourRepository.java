package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends PagingAndSortingRepository<Tour, Long> {
    @Query("SELECT t FROM Tour t WHERE " +
            "(:startTime is null or t.startTime >= :startTime) and (:endTime is null or t.endTime <= :endTime)")
    Page<Tour> searchBookingRoomsPage(Long startTime, Long endTime, Pageable pageable);
}
