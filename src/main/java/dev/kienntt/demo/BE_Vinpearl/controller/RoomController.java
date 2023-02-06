package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.model.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.Room;
import dev.kienntt.demo.BE_Vinpearl.model.RoomType;
import dev.kienntt.demo.BE_Vinpearl.service.RoomService;
import dev.kienntt.demo.BE_Vinpearl.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomTypeService roomTypeService;

    LocalDateTime localDateTime = LocalDateTime.now();

    @PostMapping("/create")
    public ResponseMessage createNewRoom(@RequestBody Room room) {
        room.setCreatedDate(localDateTime.toString());
        roomService.save(room);
        return new ResponseMessage(200, "Success", "", null);
    }

//    @PostMapping("/roomType/create")
//    public ResponseMessage createNewRoomType(@RequestBody RoomType roomType) {
//        roomType.setCreatedDate(localDateTime.toString());
//        roomTypeService.save(roomType);
//        return new ResponseMessage(200, "Success", "", null);
//    }

    @PostMapping("/roomType/create")
    public ResponseMessage createNewRoomType(@RequestBody RoomType roomType) {
        roomType.setCreatedDate(localDateTime.toString());
        roomTypeService.save(roomType);
        return new ResponseMessage(200, "Success", "", null);
    }

    @PostMapping("/update")
    public ResponseMessage updateRoom(@RequestBody Room room) {
        room.setUpdatedDate(localDateTime.toString());
        roomService.save(room);
        return new ResponseMessage(200, "Success", "", null);
    }

    @PostMapping("/roomType/update")
    public ResponseMessage updateRoomType(@RequestBody RoomType roomType) {
        roomType.setUpdatedDate(localDateTime.toString());
//        roomTypeService.save(roomType);
        return new ResponseMessage(200, "Success", "", null);
    }

    @GetMapping("/detail/{id}")
    public ResponseMessage showRoom(@PathVariable Long id) {
        Optional<Room> roomOptional = roomService.findById(id);
        return roomOptional.map(room -> new ResponseMessage(200, "Success", room, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/roomType/{id}")
    public ResponseMessage showRoomType(@PathVariable Long id) {
        Optional<RoomType> roomTypeOptional = roomTypeService.findById(id);
        return roomTypeOptional.map(roomType -> new ResponseMessage(200, "Success", roomType, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/delete/{roomId}")
    public ResponseMessage deleteRoom(@PathVariable Long roomId, @RequestHeader(value = "authorization", defaultValue = "") String auth) {
        roomService.deleteRoom(roomId);
        return new ResponseMessage(200, "Room successfully deleted!", null, null);
    }

    @GetMapping("/roomType/delete/{roomId}")
    public ResponseMessage deleteRoomType(@PathVariable Long roomId, @RequestHeader(value = "authorization", defaultValue = "") String auth) {
        roomTypeService.deleteRoomType(roomId);
        return new ResponseMessage(200, "Room successfully deleted!", null, null);
    }

    @PostMapping("/search")
    public ResponseMessage getAllRooms(@RequestBody Room room) {
        Iterable<Room> listRoom = roomService.searchRoom(room.getRoomTypeId());
        return new ResponseMessage(200, "Success", listRoom, null);
    }

//    @GetMapping("/search")
//    public ResponseMessage searchRoomsPage(@RequestParam() Long roomType,
//                                           @RequestParam(required = false) Long bookingStart,
//                                           @RequestParam(required = false) Long bookingEnd,
//                                           Pageable pageable) {
//        Page<Room> listRoom = roomService.searchRoomPage(roomType, bookingStart, bookingEnd, pageable);
//        return new ResponseMessage(200, "Success", listRoom, null);
//    }

    @GetMapping("/search")
    public ResponseMessage searchRoomsPage(@RequestParam() Long hotelId,
                                           @RequestParam() Long roomType,
                                           @RequestParam(required = false) Long bookingStart,
                                           @RequestParam(required = false) Long bookingEnd,
                                           Pageable pageable) {
        Page<Room> roomPage = roomService.searchRoomPage(hotelId, roomType, bookingStart, bookingEnd, pageable);
        return new ResponseMessage(200, "Success",
                roomPage.getContent(),
                roomPage.getTotalElements(),
                roomPage.getNumber() + 1,
                roomPage.getSize()
        );
    }

    @GetMapping("/roomType/search")
    public ResponseMessage searchRoomTypesPage(@RequestParam(required = false) Long area,
                                               @RequestParam(required = false) String name,
                                               Pageable pageable) {
        Page<RoomType> roomTypePage = roomTypeService.searchRoomTypesPage(area, name, pageable);
        return new ResponseMessage(200, "Success",
                roomTypePage.getContent(),
                roomTypePage.getTotalElements(),
                roomTypePage.getNumber() + 1,
                roomTypePage.getSize()
        );
    }

}
