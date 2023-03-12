package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TourRepository extends PagingAndSortingRepository<Tour, Long> {
//    @Query("SELECT t FROM Tour t WHERE " +
//            "(:startTime is null or t.startTime >= :startTime) and (:endTime is null or t.endTime <= :endTime)")
@Query("SELECT t FROM Tour t WHERE " +
        "(:siteId is null or t.leavingToId = :siteId) and " +
        "(:searchName is null or t.name = :searchName) and " +
        "(:status is null or t.status = :status) and " +
        "(:lengthStayId is null or t.lengthStayId = :lengthStayId) and " +
        "(:suitableId is null or t.suitableId = :suitableId) and " +
        "(:typeOfTourId is null or t.typeOfTourId = :typeOfTourId)")
    Page<Tour> searchBookingRoomsPage(Long siteId, String searchName, Long status, Long lengthStayId, Long suitableId, Long typeOfTourId, Pageable pageable);
}
