package dev.kienntt.demo.BE_Vinpearl.controller;

import dev.kienntt.demo.BE_Vinpearl.model.Hotel;
import dev.kienntt.demo.BE_Vinpearl.model.ResponseMessage;
import dev.kienntt.demo.BE_Vinpearl.model.Room;
import dev.kienntt.demo.BE_Vinpearl.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin("*")
@RequestMapping("/api/v1/hotel")
public class HotelController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private HotelService hotelService;

    LocalDateTime localDateTime = LocalDateTime.now();

//    @PostMapping("/create")
//    public ResponseMessage createNewHotel(@RequestBody Hotel hotel) {
//        System.out.println("local:" +localDateTime);
//        hotel.setCreatedDate(localDateTime.toString());
//        hotelService.save(hotel);
//        return new ResponseMessage(200, "Success", "", null);
//    }

    @PostMapping("/create")
    public ResponseMessage createHotel(@RequestParam String name,
                                       @RequestParam String email,
                                       @RequestParam String description,
                                       @RequestParam String address,
                                       @RequestParam Long area,
                                            @RequestParam MultipartFile[] images) throws IOException {
        Hotel hotel = new Hotel();
        System.out.println("local:" +localDateTime);
        hotel.setCreatedDate(localDateTime.toString());
        hotel.setName(name);
        hotel.setEmail(email);
        hotel.setDescription(description);
        hotel.setAddress(address);
        hotel.setArea(area);
        hotelService.save(hotel, images);
        return new ResponseMessage(200, "Success", "", null);
    }

    @PostMapping("/update")
    public ResponseMessage updateHotel(@RequestParam String id,
                                       @RequestParam String name,
                                       @RequestParam String email,
                                       @RequestParam String description,
                                       @RequestParam String address,
                                       @RequestParam Long area,
                                       @RequestParam MultipartFile[] images) throws IOException {
        Optional<Hotel> hotel = hotelService.findById(Long.parseLong(id));
        System.out.println("local:" +localDateTime);
        hotel.get().setCreatedDate(localDateTime.toString());
        hotel.get().setName(name);
        hotel.get().setEmail(email);
        hotel.get().setDescription(description);
        hotel.get().setAddress(address);
        hotel.get().setArea(area);
        hotelService.save(hotel.get(), images);
        System.out.println(hotel.get());
        return new ResponseMessage(200, "Success", "", null);
    }

    @GetMapping("/detail/{id}")
    public ResponseMessage showHotel(@PathVariable Long id) {
        Optional<Hotel> hotelOptional = hotelService.findById(id);
        return hotelOptional.map(hotel -> new ResponseMessage(200, "Success", hotel, null))
                .orElseGet(() -> new ResponseMessage(404, "Error", null, "No result with query"));
    }

    @GetMapping("/search")
    public ResponseMessage searchHotelsPage(@RequestParam(required = false) Long area,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String phone,
                                            Pageable pageable) {
        Page<Hotel> listRoom = hotelService.searchHotel(area, name, phone, pageable);
        return new ResponseMessage(200, "Success", listRoom, null);
    }
}
