package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.TourHotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourHotelRepository extends PagingAndSortingRepository<TourHotel, Long> {

    List<TourHotel> findByTourId(Long tourId);
    List<TourHotel> findByHotelId(Long hotelId);


    List<TourProjection> findProjectedByTourId(Long tourId);

    public interface TourProjection {
        Long getId();
        String getPrice();
    }
}
