package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.domain.request.BookingRequest;
import dev.kienntt.demo.BE_Vinpearl.domain.request.BookingRoomRequest;
import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import dev.kienntt.demo.BE_Vinpearl.repository.BookingRoomRepository;
import dev.kienntt.demo.BE_Vinpearl.service.BookingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/booking-room")
public class BookingRoomController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BookingRoomService bookingRoomService;
    LocalDateTime localDateTime = LocalDateTime.now();

    private final List<SseEmitter> emitterList = new CopyOnWriteArrayList<>();

    @PostMapping("/check-out-reminder/register")
    public SseEmitter registerCheckOutReminder() {
        SseEmitter emitter = new SseEmitter();
        emitterList.add(emitter);
        emitter.onCompletion(() -> emitterList.remove(emitter));
        return emitter;
    }

    @PostMapping("/booking")
    public ResponseEntity<?> bookRoom(@RequestBody BookingRoomRequest bookingRoomRequest) throws UnsupportedEncodingException {
        bookingRoomRequest.setIp(getRemoteIP(request));
        System.out.println(getRemoteIP(request));
        String redirectUrl = bookingRoomService.createPaymentUrl(bookingRoomRequest);
        return ResponseEntity.ok(redirectUrl);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<BookingRoom>> getBookingByCustomer(@PathVariable Long id) {
        List<BookingRoom> list = bookingRoomService.findByCustomerId(id);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/{id}/checkout")
    public ResponseEntity<?> checkoutRoom(@PathVariable Long id) {
        bookingRoomService.checkOutRoom(id);
        return ResponseEntity.ok(id);
    }

//    @PostMapping("/create")
//    public ResponseMessage createNewBookingRoom(@RequestBody BookingRoom bookingRoom) {
//        bookingRoom.setCreatedDate(localDateTime.toString());
//        bookingRoomService.save(bookingRoom);
//        return  new ResponseMessage(200, "Tạo booking thành công", null, null);
//    }
//
//    @PostMapping("/admin/create")
//    public ResponseMessage createBookingRoomAdmin(@RequestBody BookingRoom bookingRoom) {
//        bookingRoom.setCreatedDate(localDateTime.toString());
//        bookingRoomService.save(bookingRoom);
//        return  new ResponseMessage(200, "Tạo booking thành công", null, null);
//    }
//
//    @PostMapping("/update")
//    public ResponseMessage updateBookingRoom(@RequestBody BookingRoom bookingRoom) {
//        bookingRoom.setCreatedDate(localDateTime.toString());
//        bookingRoomService.save(bookingRoom);
//        return  new ResponseMessage(200, "Cập nhật booking thành công", null, null);
//    }

    @GetMapping("/detail/{id}")
    public ResponseMessage detailBookingRoom(@PathVariable Long id) {
        Optional<BookingRoom> bookingRoomOptional = bookingRoomService.findById(id);
        return bookingRoomOptional.map(bookingRoom -> new ResponseMessage(200, "Success", bookingRoom, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/search")
    public ResponseMessage searchRoomsPage(@RequestParam(required = false) Long startTime,
                                           @RequestParam(required = false) Long endTime,
                                           Pageable pageable) {
        Page<BookingRoom> list = bookingRoomService.searchBookingRoomsPage(startTime, endTime, pageable);
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
