package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.domain.request.BookingRoomRequest;
import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookingTourService {
    Iterable findAll();

    Optional findById(Long id);

    List<BookingTour> findByCustomerId(Long id);

    BookingTour save(BookingTour bookingTour);

    void remove(Long id);

    List<Tour> getTopTours(int limit);

    List<Customer> getTop5Customer();

    Map<String, Long> getBookingTourCountByMonth();

    Page<BookingTour> searchBookingTour(Long customerId, String code, Long status, Long startTime, Long endTime, Pageable pageable);

    BookingTour saveBookingTour(BookingTour bookingTour) throws UnsupportedEncodingException;
}
