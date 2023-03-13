package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.model.Tour;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookingTourService {
    Iterable findAll();

    Optional findById(Long id);

    BookingTour save(BookingTour bookingTour);

    void remove(Long id);

    List<Tour> getTopTours(int limit);

    List<Customer> getTop5Customer();

    Map<String, Long> getBookingTourCountByMonth();
}
