package dev.tienvv.demo.BE_Vinpearl.service.serviceImpl;

import dev.tienvv.demo.BE_Vinpearl.domain.request.BookingTourStatistic;
import dev.tienvv.demo.BE_Vinpearl.model.BookingRoom;
import dev.tienvv.demo.BE_Vinpearl.model.BookingTour;
import dev.tienvv.demo.BE_Vinpearl.repository.BookingRoomRepository;
import dev.tienvv.demo.BE_Vinpearl.repository.BookingTourRepository;
import dev.tienvv.demo.BE_Vinpearl.repository.TourRepository;
import dev.tienvv.demo.BE_Vinpearl.service.DashboardService;
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

    public List<Map<String, Object>> getTotal(Long startTime, Long endTime) {
        List<Map<String, Object>> result = new ArrayList<>();

        Long totalBookingTour = bookingTourRepository.countBookingTour(startTime, endTime);
        Map<String, Object> bookingTourMap = new HashMap<>();
        bookingTourMap.put("name", "booking_tour");
        bookingTourMap.put("total", totalBookingTour);
        result.add(bookingTourMap);

        Long totalBookingRoom = bookingRoomRepository.countBookingRoom(startTime, endTime);
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

    @Override
    public List<BookingTourStatistic> statistics() {
        List<BookingTour> bookingTours = bookingTourRepository.findAllBookingTour();
        List<BookingRoom> bookingRooms = bookingRoomRepository.findAllBookingRoom();

        List<BookingTourStatistic> result = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String month = String.format("Tháng %d", i);
            int finalI = i;
            long countTour = bookingTours.stream()
                    .filter(bookingTour -> bookingTour.getPaymentDate() != null && bookingTour.getPaymentDate().getMonthValue() == finalI)
                    .count();
            long countRoom = bookingRooms.stream()
                    .filter(bookingRoom -> bookingRoom.getPaymentDate() != null && bookingRoom.getPaymentDate().getMonthValue() == finalI)
                    .count();
            result.add(new BookingTourStatistic(month, countTour, countRoom));
        }
        return result;
    }
}
