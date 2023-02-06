package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.Room;
import dev.kienntt.demo.BE_Vinpearl.service.BookingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/bookingRoom")
public class BookingRoomController {
    @Autowired
    private BookingRoomService bookingRoomService;

    LocalDateTime localDateTime = LocalDateTime.now();

    @PostMapping("/create")
    public ResponseMessage createNewBookingRoom(@RequestBody BookingRoom bookingRoom) {
        bookingRoom.setCreatedDate(localDateTime.toString());
        bookingRoomService.save(bookingRoom);
        return  new ResponseMessage(200, "Tạo booking thành công", null, null);
    }

    @PostMapping("/update")
    public ResponseMessage updateBookingRoom(@RequestBody BookingRoom bookingRoom) {
        bookingRoom.setCreatedDate(localDateTime.toString());
        bookingRoomService.save(bookingRoom);
        return  new ResponseMessage(200, "Cập nhật booking thành công", null, null);
    }

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
