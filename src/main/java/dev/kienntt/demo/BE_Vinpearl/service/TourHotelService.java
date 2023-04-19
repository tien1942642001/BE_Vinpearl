package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.TourHotel;
import dev.kienntt.demo.BE_Vinpearl.repository.TourHotelRepository;

import java.util.List;

public interface TourHotelService {
    List<TourHotel> findTourHotelByTourId(Long tourId);

    List<TourHotel> findTourHotelByHotelId(Long hotelId);

    List<TourHotel> findAvailableHotelsByTourIdLong(Long tourId);
}
