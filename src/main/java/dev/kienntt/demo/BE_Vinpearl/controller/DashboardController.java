package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.service.BookingRoomService;
import dev.kienntt.demo.BE_Vinpearl.service.BookingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    @Autowired
    private BookingRoomService bookingRoomService;

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
}
