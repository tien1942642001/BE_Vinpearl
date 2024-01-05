package dev.tienvv.demo.BE_Vinpearl.controller;

import dev.tienvv.demo.BE_Vinpearl.base.ResponseMessage;
import dev.tienvv.demo.BE_Vinpearl.config.JwtTokenProvider;
import dev.tienvv.demo.BE_Vinpearl.model.*;
import dev.tienvv.demo.BE_Vinpearl.service.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private TourService tourService;

    @Autowired
    private RoomTypeService roomTypeService;

    @PostMapping("/user/register")
    public ResponseMessage create(@RequestBody User user) {
        String md5Password = DigestUtils.md5Hex(user.getPassword()).toUpperCase();
        user.setPassword(md5Password);
        userService.save(user);
        return new ResponseMessage(200, "Tạo tài khoản thành công", null, null);
    }
    @PostMapping("/user/login")
    public ResponseMessage login(@RequestBody User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User userDb = userService.findByEmail(user.getEmail());
        String md5Password = DigestUtils.md5Hex(user.getPassword()).toUpperCase();
//        System.out.println("Verify: " + userDb.getPassword().equals(md5Password));
        if (!userDb.getPassword().equals(md5Password)) {
            return new ResponseMessage(404, "Fail", "Tài đăng nhập/ Mật khẩu không đúng", null);
        }
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        userDb.setToken(jwtTokenProvider.createJwtSignedHMAC(userDb));
        return new ResponseMessage(200, "Success", userDb, null);
    }

    @PostMapping("/customer/register")
    public ResponseMessage createCustomer(@RequestBody Customer customer) {
        String md5Password = DigestUtils.md5Hex(customer.getPassword()).toUpperCase();
        customer.setPassword(md5Password);
        customerService.save(customer);
        return new ResponseMessage(200, "Tạo tài khoản thành công", "", null);
    }

    @PostMapping("/customer/login")
    public ResponseMessage loginCustomer(@RequestBody Customer customer) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Customer customerDb = customerService.findByEmail(customer.getEmail());
        String md5Password = DigestUtils.md5Hex(customer.getPassword()).toUpperCase();
        if (!customerDb.getEmail().equals(customer.getEmail())) {
            return new ResponseMessage(400, "Fail","Tài khoản không tồn tại", null);
        } else if (!customerDb.getPassword().equals(md5Password)) {
            return new ResponseMessage(400, "Fail", "Mật khẩu không đúng", null);
        }
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        customerDb.setToken(jwtTokenProvider.createJwtSignedHMAC1(customerDb));
        return new ResponseMessage(200, "Success", customerDb, null);
    }

    @GetMapping("/hotel/search/customer")
    public ResponseMessage getListHotel(@RequestParam(required = false) Long siteId,
                                        @RequestParam(required = false) Long totalRoom,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String phone,
                                        Pageable pageable) {
        Page<Hotel> listHotel = hotelService.getListHotel(siteId, name, totalRoom, phone, pageable);
        return new ResponseMessage(200, "Success", listHotel, null);
    }

    @GetMapping("/tour/search")
    public ResponseMessage searchTour(@RequestParam(required = false) Long siteId,
                                      @RequestParam(required = false) String searchName,
                                      @RequestParam(required = false) Long status,
                                      @RequestParam(required = false) List<Long> lengthStayIds,
                                      @RequestParam(required = false) List<Long> suitableIds,
                                      @RequestParam(required = false) List<Long> typeOfTours,
                                      @RequestParam(required = false) Long customerId,
                                      Pageable pageable) {
        Page<Tour> listTour = tourService.searchTourPage(customerId, siteId, searchName, status, lengthStayIds, suitableIds, typeOfTours, pageable);
        return new ResponseMessage(200, "Success", listTour, null);
    }

    @GetMapping("room/room-type/search")
    public ResponseMessage searchRoomTypesPage( @RequestParam(required = false) Long numberPerson,
                                                @RequestParam(required = false) String hotelName,
                                                @RequestParam(required = false) Long acreage,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) Long startTime,
                                                @RequestParam(required = false) Long endTime,
                                                Pageable pageable) {

        Page<RoomType> listRoom = roomTypeService.searchRoomTypesPage(numberPerson, hotelName, acreage, name, startTime, endTime, pageable);
        return new ResponseMessage(200, "Success", listRoom, null);
    }
}
