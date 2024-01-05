package dev.tienvv.demo.BE_Vinpearl.service.serviceImpl;

import dev.tienvv.demo.BE_Vinpearl.model.Hotel;
import dev.tienvv.demo.BE_Vinpearl.model.Tour;
import dev.tienvv.demo.BE_Vinpearl.model.TourHotel;
import dev.tienvv.demo.BE_Vinpearl.repository.TourHotelRepository;
import dev.tienvv.demo.BE_Vinpearl.service.TourHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourHotelServiceImpl implements TourHotelService {
    @Autowired
    private TourHotelRepository tourHotelRepository;

    public List<TourHotel> findTourHotelByTourId(Long tourId) {
        return tourHotelRepository.findByTourId(tourId);
    }

    public List<TourHotel> findTourHotelByHotelId(Long hotelId) {
        return tourHotelRepository.findByHotelId(hotelId);
    }

    public List<TourHotel> findAvailableHotelsByTourIdLong(Long tourId) {
        return tourHotelRepository.findAvailableHotelsByTourIdLong(tourId);
    }
}
