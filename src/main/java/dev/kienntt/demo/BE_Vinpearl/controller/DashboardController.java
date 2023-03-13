package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.service.BookingRoomService;
import dev.kienntt.demo.BE_Vinpearl.service.BookingTicketService;
import dev.kienntt.demo.BE_Vinpearl.service.BookingTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    @Autowired
    private BookingRoomService bookingRoomService;

    @Autowired
    private BookingTourService bookingTourService;

    @Autowired
    private BookingTicketService bookingTicketService;

    @GetMapping("/booking-room")
    public ResponseMessage findBookingRoom(@RequestParam Long startMonth,@RequestParam Long endMonth) {
        return new ResponseMessage(200, "Success", bookingRoomService.findAllByMonth(startMonth, endMonth), null);
    }

    @GetMapping("/booking-ticket")
    public ResponseMessage findBookingTicket(@RequestParam Long startMonth,@RequestParam Long endMonth) {
        return new ResponseMessage(200, "Success", bookingTicketService.findAllByMonth(startMonth, endMonth), null);
    }

//    thống kê số người đặt tour theo tháng
    @GetMapping("/booking-tour-by-month")
    public ResponseEntity<Map<String, Long>> getBookingTourCountByMonth() {
        Map<String, Long> test = bookingTourService.getBookingTourCountByMonth();
        return ResponseEntity.ok().body(test);
    }

    @GetMapping("/getTop5Customer")
    public ResponseEntity<Map<String, Long>> getTop5Customer() {
        Map<String, Long> test = bookingTourService.getBookingTourCountByMonth();
        return ResponseEntity.ok().body(test);
    }

//    thống kê số người đặt phòng theo tháng
    @GetMapping("/booking-room-by-month")
    public ResponseEntity<Map<String, Long>> getBookingRoomCountByMonth() {
        Map<String, Long> test = bookingRoomService.getBookingRoomCountByMonth();
        return ResponseEntity.ok().body(test);
    }
}
