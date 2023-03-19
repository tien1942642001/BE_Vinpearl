package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.domain.request.BookingRoomRequest;
import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.model.Tour;
import dev.kienntt.demo.BE_Vinpearl.service.BookingTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/booking-tour")
public class BookingTourController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BookingTourService bookingTourService;

    @PostMapping("/create")
    public ResponseMessage create(@RequestBody BookingTour bookingTour) {
        bookingTourService.save(bookingTour);
        return new ResponseMessage(200, "Đặt tour thành công", "", null);
    }

    @PostMapping("/booking")
    public ResponseMessage createNewBookingtour(@RequestBody BookingTour bookingTour) throws UnsupportedEncodingException {
        BookingTour bookingTour1 = bookingTourService.saveBookingTour(bookingTour);
        return  new ResponseMessage(200, "OK", bookingTour1, null);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<BookingTour>> getBookingByCustomer(@PathVariable Long id) {
        List<BookingTour> list = bookingTourService.findByCustomerId(id);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/detail/{id}")
    public ResponseMessage detailBookingRoom(@PathVariable Long id) {
        Optional<BookingTour> bookingTourOptional = bookingTourService.findById(id);
        return bookingTourOptional.map(bookingTour -> new ResponseMessage(200, "Success", bookingTour, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
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

    @GetMapping("/search")
    public ResponseMessage searchBookingTour(@RequestParam(required = false) Long customerId,
                                           @RequestParam(required = false) String code,
                                           @RequestParam(required = false) Long status,
                                           @RequestParam(required = false) Long startTime,
                                           @RequestParam(required = false) Long endTime,
                                           Pageable pageable) {
        Page<BookingTour> list = bookingTourService.searchBookingTour(customerId, code, status, startTime, endTime, pageable);
        return new ResponseMessage(200, "Success", list, null);
    }

    public String getRemoteIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getHeader("X-Real-IP");
        }
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
//            ipAddress = request.getLocalAddr();
        }
        return ipAddress;
    }
}
