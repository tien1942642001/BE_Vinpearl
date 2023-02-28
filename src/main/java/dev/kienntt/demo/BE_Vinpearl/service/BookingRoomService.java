package dev.kienntt.demo.BE_Vinpearl.service;


import dev.kienntt.demo.BE_Vinpearl.domain.request.BookingRoomRequest;
import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

public interface BookingRoomService {
    List<BookingRoom> findAll();

    Optional<BookingRoom> findById(Long id);

    BookingRoom save(BookingRoomRequest bookingRoomRequest);

    BookingRoom update(Long bookingRoomId, BookingRoom bookingRoomDetails) throws AccessDeniedException;

    void deleteBookingRoom(Long id);

    Page<BookingRoom> searchBookingRoomsPage(Long startTime, Long endTime, Pageable pageable);

    Long findAllByMonth(Long startMonth, Long endMonth);

    BookingRoom checkOutRoom(Long id);
}
