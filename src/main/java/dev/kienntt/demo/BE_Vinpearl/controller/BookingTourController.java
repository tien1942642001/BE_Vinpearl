package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.model.Tour;
import dev.kienntt.demo.BE_Vinpearl.service.BookingTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/booking-tour")
public class BookingTourController {
    @Autowired
    private BookingTourService bookingTourService;

    @PostMapping("/create")
    public ResponseMessage create(@RequestBody BookingTour bookingTour) {
        bookingTourService.save(bookingTour);
        return new ResponseMessage(200, "Đặt tour thành công", "", null);
    }

    @GetMapping("/top-tours")
    public ResponseEntity<List<Tour>> getTopTours(@RequestParam int limit) {
        List<Tour> topTours = bookingTourService.getTopTours(limit);
        return ResponseEntity.ok().body(topTours);
    }

    @GetMapping("/top-customers")
    public ResponseEntity<List<Customer>> getTopCustomers() {
        List<Customer> topCustomers = bookingTourService.getTop5Customer();
        return ResponseEntity.ok().body(topCustomers);
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getBooking() {
        List<Customer> topCustomers = bookingTourService.getTop5Customer();
        return ResponseEntity.ok().body(topCustomers);
    }
}
