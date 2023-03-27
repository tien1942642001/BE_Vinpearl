package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.base.ResponsePage;
import dev.kienntt.demo.BE_Vinpearl.model.*;
import dev.kienntt.demo.BE_Vinpearl.service.RoomService;
import dev.kienntt.demo.BE_Vinpearl.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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
        room.setCreatedBy(room.getCreator());
        roomService.save(room);
        return new ResponseMessage(200, "Success", "", null);
    }

//    @PostMapping("/room-type/create")
//    public ResponseMessage createNewRoomType(@ModelAttribute RoomType roomType, MultipartFile[] images) throws IOException {
//        roomType.setCreatedDate(localDateTime.toString());
//        roomTypeService.save(roomType, images);
//        return new ResponseMessage(200, "Success", "", null);
//    }

    @PostMapping("/room-type/create")
    public ResponseMessage createNewRoomType(@RequestParam String name,
                                       @RequestParam Long acreage,
                                       @RequestParam Long numberAdult,
                                       @RequestParam Long numberChildren,
                                       @RequestParam(required = false) String description,
                                       @RequestParam Long hotelId,
                                       @RequestParam(required = false) Long id,
                                       @RequestParam Long numberOfRooms,
                                       @RequestParam MultipartFile[] images) throws IOException {
        RoomType roomType = new RoomType();
        roomType.setCreatedDate(localDateTime.toString());
        roomType.setCreatedBy(roomType.getCreator());
        roomType.setName(name);
        roomType.setAcreage(acreage);
        roomType.setNumberAdult(numberAdult);
        roomType.setNumberChildren(numberChildren);
        roomType.setDescription(description);
        roomType.setHotelId(hotelId);
        roomType.setNumberOfRooms(numberOfRooms);
        roomType.setRemainingOfRooms(numberOfRooms);
        if (id != null) {
            roomType.setId(id);
        }
        roomTypeService.save(roomType, images);
        return new ResponseMessage(200, "Success", "", null);
    }

    @PostMapping("/update")
    public ResponseMessage updateRoom(@RequestBody Room room) {
        room.setUpdatedDate(localDateTime.toString());
        room.setCreatedBy(room.getCreator());
        roomService.save(room);
        return new ResponseMessage(200, "Success", room, null);
    }

    @PostMapping("/room-type/update")
    public ResponseMessage updateRoomType(@RequestBody RoomType roomType) {
        roomType.setUpdatedDate(localDateTime.toString());
        roomTypeService.save(roomType);
        return new ResponseMessage(200, "Success", roomType, null);
    }

    @GetMapping("/detail/{id}")
    public ResponseMessage showRoom(@PathVariable Long id) {
        Optional<Room> roomOptional = roomService.findById(id);
        return roomOptional.map(room -> new ResponseMessage(200, "Success", room, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/room-type/detail/{id}")
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

    @GetMapping("/room-type/delete/{roomId}")
    public ResponseMessage deleteRoomType(@PathVariable Long roomId, @RequestHeader(value = "authorization", defaultValue = "") String auth) {
        roomTypeService.deleteRoomType(roomId);
        return new ResponseMessage(200, "Room successfully deleted!", null, null);
    }

    @PostMapping("/search1")
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
    public ResponseMessage searchRoomsPage(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String roomType,
                                        @RequestParam(required = false) Long status,
                                        Pageable pageable) {
        Page<Room> listRoom = roomService.searchRoomPage(name, roomType, status, pageable);
//        return new ResponsePage(200, "Success",
//                roomPage.getContent(),
//                roomPage.getTotalElements(),
//                roomPage.getNumber() + 1,
//                roomPage.getSize()
//        );
        return new ResponseMessage(200, "Success", listRoom, null);
    }

    @GetMapping("/room-type/search")
    public ResponseMessage searchRoomTypesPage( @RequestParam(required = false) Long numberPerson,
                                                @RequestParam(required = false) String hotelName,
                                             @RequestParam(required = false) Long acreage,
                                             @RequestParam(required = false) String name,
                                             Pageable pageable) {

        Page<RoomType> listRoom = roomTypeService.searchRoomTypesPage(numberPerson, hotelName, acreage, name, pageable);
        return new ResponseMessage(200, "Success", listRoom, null);
    }

    @GetMapping("/room-type/findByHotelId/{hotelId}")
    public ResponseMessage findAllRoomType(@PathVariable Long hotelId) {
        return new ResponseMessage(200, "Success", roomTypeService.findRoomTypeByHotelId(hotelId), null);
    }

    @GetMapping("/room-type/findAll")
    public ResponseMessage findAllRoomType() {
        return new ResponseMessage(200, "Success", roomTypeService.findAll(), null);
    }

}
