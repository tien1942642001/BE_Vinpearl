package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.repository.BookingTourRepository;
import dev.kienntt.demo.BE_Vinpearl.service.BookingTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingTourServiceImpl implements BookingTourService {
    @Autowired
    private BookingTourRepository bookingTourRepository;

    @Override
    public Iterable findAll() {
        return bookingTourRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return bookingTourRepository.findById(id);
    }

    @Override
    public BookingTour save(BookingTour bookingTour) {
        return bookingTourRepository.save(bookingTour);
    }

    @Override
    public void remove(Long id) {
        bookingTourRepository.deleteById(id);
    }
}
