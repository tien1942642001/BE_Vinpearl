package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    LocalDateTime localDateTime = LocalDateTime.now();

//    @PostMapping("/create")
//    public ResponseMessage createNewHotel(@ModelAttribute Hotel hotel, MultipartFile[] images) throws IOException {
//        hotel.setCreatedDate(localDateTime.toString());
//        hotelService.save(hotel, images);
//        return new ResponseMessage(200, "Success", "", null);
//    }

    @PostMapping("/create")
    public ResponseMessage createHotel(@RequestParam String name,
                                       @RequestParam String email,
                                       @RequestParam String description,
                                       @RequestParam String address,
                                       @RequestParam Long acreage,
                                       @RequestParam String phone,
                                       @RequestParam Long totalRoom,
                                       @RequestParam Long siteId,
                                            @RequestParam MultipartFile[] images) throws IOException {
        Hotel hotel = new Hotel();
        hotel.setCreatedDate(localDateTime.toString());
        hotel.setCreatedBy(hotel.getCreator());
        hotel.setName(name);
        hotel.setEmail(email);
        hotel.setDescription(description);
        hotel.setAddress(address);
        hotel.setPhone(phone);
        hotel.setAcreage(acreage);
        hotel.setSiteId(siteId);
        hotel.setTotalRoom(totalRoom);
        hotelService.save(hotel, images);
        return new ResponseMessage(200, "Success", "", null);
    }

    @PostMapping("/update")
    public ResponseMessage updateHotel(@RequestParam String id,
                                       @RequestParam String name,
                                       @RequestParam String email,
                                       @RequestParam String description,
                                       @RequestParam String address,
                                       @RequestParam Long acreage,
                                       @RequestParam MultipartFile[] images) throws IOException {
        Optional<Hotel> hotel = hotelService.findById(Long.parseLong(id));
        hotel.get().setCreatedDate(localDateTime.toString());
        hotel.get().setCreatedBy(hotel.get().getCreator());
        hotel.get().setName(name);
        hotel.get().setEmail(email);
        hotel.get().setDescription(description);
        hotel.get().setAddress(address);
        hotel.get().setAcreage(acreage);
        hotelService.save(hotel.get(), images);
        System.out.println(hotel.get());
        return new ResponseMessage(200, "Success", "", null);
    }

    @GetMapping("/findAll")
    public ResponseMessage findAllRoomType() {
        return new ResponseMessage(200, "Success", hotelService.findAll(), null);
    }

    @GetMapping("/detail/{id}")
    public ResponseMessage showHotel(@PathVariable Long id) {
        Optional<Hotel> hotelOptional = hotelService.findById(id);
        return hotelOptional.map(hotel -> new ResponseMessage(200, "Success", hotel, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/search")
    public ResponseMessage searchHotelsPage(@RequestParam(required = false) Long siteId,
                                            @RequestParam(required = false) Long totalRoom,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String phone,
                                            Pageable pageable) {
        Page<Hotel> listHotel = hotelService.searchHotel(siteId, name, totalRoom, phone, pageable);
        return new ResponseMessage(200, "Success", listHotel, null);
    }
}
