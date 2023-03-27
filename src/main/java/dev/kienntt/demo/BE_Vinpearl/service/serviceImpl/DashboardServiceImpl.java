package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.repository.BookingRoomRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.BookingTourRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.TourRepository;
import dev.kienntt.demo.BE_Vinpearl.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private BookingTourRepository bookingTourRepository;

    @Autowired
    private BookingRoomRepository bookingRoomRepository;

    @Autowired
    private TourRepository tourRepository;

    public List<Map<String, Object>> getTotal() {
        List<Map<String, Object>> result = new ArrayList<>();

        Long totalBookingTour = bookingTourRepository.count();
        Map<String, Object> bookingTourMap = new HashMap<>();
        bookingTourMap.put("name", "booking_tour");
        bookingTourMap.put("total", totalBookingTour);
        result.add(bookingTourMap);

        Long totalBookingRoom = bookingRoomRepository.count();
        Map<String, Object> bookingRoomMap = new HashMap<>();
        bookingRoomMap.put("name", "booking_room");
        bookingRoomMap.put("total", totalBookingRoom);
        result.add(bookingRoomMap);

        Long totalTour = tourRepository.count();
        Map<String, Object> tourMap = new HashMap<>();
        tourMap.put("name", "tour");
        tourMap.put("total", totalTour);
        result.add(tourMap);

        return result;
    }
}
