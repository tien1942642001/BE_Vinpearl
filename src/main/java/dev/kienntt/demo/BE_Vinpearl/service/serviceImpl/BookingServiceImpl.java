package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.Room;
import dev.kienntt.demo.BE_Vinpearl.repository.BookingRoomRepository;
import dev.kienntt.demo.BE_Vinpearl.service.BookingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingRoomService {
    @Autowired
    private BookingRoomRepository bookingRoomRepository;

//    @Override
//    public List<BookingRoom> findAll() {
//        return bookingRoomRepository.findAll();
//    }

    @Override
    public List<BookingRoom> findAll() {
        return (List<BookingRoom>) bookingRoomRepository.findAll();
    }

    @Override
    public Optional<BookingRoom> findById(Long id) {
        return bookingRoomRepository.findById(id);
    }

    @Override
    public BookingRoom save(BookingRoom bookingRoom) {
        return bookingRoomRepository.save(bookingRoom);
    }

    @Override
    public void deleteBookingRoom(Long id) {
        try{
            bookingRoomRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Page<BookingRoom> searchBookingRoomsPage(Long startTime, Long endTime, Pageable pageable) {
//        PageRequest page_req = new PageRequest(0, buildingId, Sort.Direction.DESC, "idNode");
        return bookingRoomRepository.searchBookingRoomsPage(startTime, endTime, pageable);
    }
}
