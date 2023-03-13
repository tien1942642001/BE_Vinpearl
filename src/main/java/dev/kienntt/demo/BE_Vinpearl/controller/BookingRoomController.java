package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.domain.request.BookingRequest;
import dev.kienntt.demo.BE_Vinpearl.domain.request.BookingRoomRequest;
import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
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

import java.io.IOException;
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
    private BookingRoomService bookingRoomService;
    LocalDateTime localDateTime = LocalDateTime.now();

//    @GetMapping("/booking-room/check-out-reminder")
//    public List<BookingRoom> getCheckOutReminder() {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime oneHourLater = now.plusHours(1);
//        return bookingRoomRepository.findByCheckOutTimeBetween(now, oneHourLater);
//    }

//    @GetMapping("/booking-room/check-out-reminder/sse")
//    public SseEmitter getCheckOutReminderSse() {
//        SseEmitter emitter = new SseEmitter();
//        List<BookingRoom> reminderList = getCheckOutReminder();
//
//        for (BookingRoom bookingRoom : reminderList) {
//            try {
//                emitter.send(SseEmitter.event()
//                        .data(bookingRoom, MediaType.APPLICATION_JSON)
//                        .name("check-out-reminder"));
//            } catch (IOException e) {
//                emitter.completeWithError(e);
//                break;
//            }
//        }
//
//        return emitter;
//    }

//    @Bean
//    public ScheduledExecutorService reminderExecutor() {
//        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
//        executor.setRemoveOnCancelPolicy(true);
//        executor.scheduleWithFixedDelay(() -> {
//            List<BookingRoom> reminderList = getCheckOutReminder();
//            for (SseEmitter emitter : emitterList) {
//                try {
//                    for (BookingRoom bookingRoom : reminderList) {
//                        emitter.send(SseEmitter.event()
//                                .data(bookingRoom, MediaType.APPLICATION_JSON)
//                                .name("check-out-reminder"));
//                    }
//                } catch (IOException e) {
//                    emitterList.remove(emitter);
//                }
//            }
//        }, 0, 10, TimeUnit.MINUTES);
//
//        return executor;
//    }

    private final List<SseEmitter> emitterList = new CopyOnWriteArrayList<>();

    @PostMapping("/check-out-reminder/register")
    public SseEmitter registerCheckOutReminder() {
        SseEmitter emitter = new SseEmitter();
        emitterList.add(emitter);
        emitter.onCompletion(() -> emitterList.remove(emitter));
        return emitter;
    }

    @PostMapping("/booking")
    public ResponseEntity<?> bookRoom(@RequestBody BookingRoomRequest bookingRoomRequest) {
        bookingRoomService.save(bookingRoomRequest);
        return ResponseEntity.ok(bookingRoomRequest);
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
}
