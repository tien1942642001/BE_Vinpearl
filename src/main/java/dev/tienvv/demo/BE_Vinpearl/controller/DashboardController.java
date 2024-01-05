package dev.tienvv.demo.BE_Vinpearl.controller;

import dev.tienvv.demo.BE_Vinpearl.base.ResponseMessage;
import dev.tienvv.demo.BE_Vinpearl.domain.dto.CustomerStats;
import dev.tienvv.demo.BE_Vinpearl.domain.dto.CustomerTop5Dto;
import dev.tienvv.demo.BE_Vinpearl.domain.request.BookingTourStatistic;
import dev.tienvv.demo.BE_Vinpearl.model.BookingTour;
import dev.tienvv.demo.BE_Vinpearl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    @Autowired
    private BookingRoomService bookingRoomService;

    @Autowired
    private BookingTourService bookingTourService;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/total")
    public ResponseMessage getTotal(@RequestParam(required = false) Long startTime, @RequestParam(required = false) Long endTime) {
        return new ResponseMessage(200, "Success", dashboardService.getTotal(startTime, endTime), null);
    }

    @GetMapping("/booking-room")
    public ResponseMessage findBookingRoom(@RequestParam Long startMonth,@RequestParam Long endMonth) {
        return new ResponseMessage(200, "Success", bookingRoomService.findAllByMonth(startMonth, endMonth), null);
    }

//    thống kê số người đặt tour theo tháng
    @GetMapping("/booking-tour-by-month")
    public ResponseEntity<Map<String, Long>> getBookingTourCountByMonth() {
        Map<String, Long> test = bookingTourService.getBookingTourCountByMonth();
        return ResponseEntity.ok().body(test);
    }

    @GetMapping("/getTop5Customer")
    public ResponseMessage getTop5Customer() {
        List<CustomerTop5Dto> list = customerService.getTop5();
        return new ResponseMessage(200, "Success", list, null);
    }

//    thống kê số người đặt phòng theo tháng
    @GetMapping("/statistics")
    public  ResponseMessage getBookingTourStatistics() {
        List<BookingTourStatistic> list = dashboardService.statistics();
        return new ResponseMessage(200, "Success", list, null);
    }

    @GetMapping("/customer-statistics")
    public ResponseMessage getCustomerStatistics(@RequestParam Long startTime,
                                              @RequestParam Long endTime) {
//        long oldCustomers = customerService.countOldCustomers(startTime, endTime);
//        long newCustomers = customerService.countNewCustomers(startTime, endTime);
//
//        Map<String, Long> stats = new HashMap<>();
//        stats.put("oldCustomers", oldCustomers);
//        stats.put("newCustomers", newCustomers);

//        CustomerStats customerStats = customerService.countCustomersByType(startTime, endTime);

        return new ResponseMessage(200, "Success", null, null);
    }
}
