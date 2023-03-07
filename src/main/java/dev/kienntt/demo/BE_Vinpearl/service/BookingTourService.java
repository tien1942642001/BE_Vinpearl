package dev.kienntt.demo.BE_Vinpearl.service;

import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;

import java.util.Optional;

public interface BookingTourService {
    Iterable findAll();

    Optional findById(Long id);

    BookingTour save(BookingTour bookingTour);

    void remove(Long id);
}
