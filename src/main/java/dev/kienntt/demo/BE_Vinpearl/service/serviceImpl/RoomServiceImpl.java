package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.Room;
import dev.kienntt.demo.BE_Vinpearl.model.RoomType;
import dev.kienntt.demo.BE_Vinpearl.repository.RoomRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.RoomTypeRepository;
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

    @Autowired
    private RoomTypeRepository roomTypeRepository;

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
        Optional<RoomType> roomType = roomTypeRepository.findById(room.getRoomTypeId());
        int numRooms = roomRepository.countRooms(room.getRoomTypeId());
        Room room1 =  roomRepository.save(room);
        if (numRooms >= roomType.get().getNumberOfRooms()) {
            throw new RuntimeException("Đã đạt tối đa số lượng phòng cho phép");
        }
        room1.setNumberRoom(String.format("RM%3d", room1.getId()));
        return roomRepository.save(room1);
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
    public Page<Room> searchRoomPage(String name, String roomType, Long status, Pageable pageable) {
        return roomRepository.searchRoomsPage(name, roomType, status, pageable);
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
