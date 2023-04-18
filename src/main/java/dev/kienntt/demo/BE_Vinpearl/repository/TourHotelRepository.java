package dev.kienntt.demo.BE_Vinpearl.repository;

import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.TourHotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourHotelRepository extends PagingAndSortingRepository<TourHotel, Long> {

//    @Query("select new dev.kienntt.demo.BE_Vinpearl.domain.dto.TourHotelDto(t.id, ) from Tour t " +
//            "left join TourHotel th on t.id = th.tourId " +
//            "left join Hotel h on h.id = th.hotelId " +
//            "left join RoomType rt on rt.hotelId = h.id " +
//            "left join Room r on rt.id = r.roomTypeId " +
//            "left join BookingRoom br on r.id = br.roomId " +
//            "where t.id = 4 and (br.checkIn > t.endDate or br.checkOut < t.startDate)" +
//            "GROUP BY h.id")
    List<TourHotel> findByTourId(Long tourId);
    List<TourHotel> findByHotelId(Long hotelId);
    TourHotel findByTourIdAndHotelId(Long tourId, Long hotelId);

    @Query("SELECT DISTINCT th FROM TourHotel th " +
            "left join Hotel h on h.id = th.hotelId " +
            "left join Tour t on t.id = th.tourId " +
            "left join RoomType rt on rt.hotelId = h.id " +
            "left join Service s on rt.id = s.roomTypeId " +
            "left join Room r on rt.id = r.roomTypeId " +
            "left join ImageHotel ih on h.id = ih.hotelId " +
            "left join BookingRoom br on r.id = br.roomId " +
//            "where th.id = :tourId AND NOT EXISTS (SELECT 1 FROM BookingRoom br WHERE br.checkIn > t.endDate or br.checkOut < t.startDate)" )
            "where t.id = :tourId and (br.checkIn > t.endDate or br.checkOut < t.startDate)" +
            "GROUP BY h.id")
    List<TourHotel> findAvailableHotelsByTourIdLong(Long tourId);
}
