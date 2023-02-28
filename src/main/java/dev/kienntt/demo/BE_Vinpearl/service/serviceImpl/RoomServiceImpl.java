package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.Room;
import dev.kienntt.demo.BE_Vinpearl.model.RoomType;
import dev.kienntt.demo.BE_Vinpearl.repository.RoomRepository;
import dev.kienntt.demo.BE_Vinpearl.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        return (List<Room>) roomRepository.findAll();
    }

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        try{
            roomRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<Room> searchRoom(Long roomType) {
        return roomRepository.searchRooms(roomType);
    }

    @Override
    public Page<Room> searchRoomPage(Long hotelId, Long roomType, Long bookingStart, Long bookingEnd, Pageable pageable) {
        return roomRepository.searchRoomsPage(roomType, pageable);
    }

//    @Override
//    public List<Room> test(Long roomType, Long bookingStart, Long bookingEnd, Pageable pageable) {
//        List<Room> listRooms = roomRepository.searchRoomsPage(roomType, pageable).getContent();
//        List<Room> result =  new ArrayList<>();
//        listRooms.forEach(room -> {
//            room.getBookingRooms().stream().filter(bookingRoom -> (
//                    bookingRoom.getStartTime() >= bookingStart && bookingRoom.getEndTime() <= bookingEnd)
//            ).forEach(x ->
//                    System.out.println(x)
//            );
//            System.out.println("bookingStart: "+bookingStart);
////            System.out.println("emptyRooms: "+emptyRooms);
////            result.add((Room) emptyRooms);
//        });
//        return null;
//    }
}
