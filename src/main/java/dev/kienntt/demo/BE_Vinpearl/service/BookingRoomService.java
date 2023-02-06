package dev.kienntt.demo.BE_Vinpearl.service;


import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookingRoomService {
    List<BookingRoom> findAll();

    Optional<BookingRoom> findById(Long id);

    BookingRoom save(BookingRoom bookingRoom);

    void deleteBookingRoom(Long id);

    Page<BookingRoom> searchBookingRoomsPage(Long startTime, Long endTime, Pageable pageable);
}
