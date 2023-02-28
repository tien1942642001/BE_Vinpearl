package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.domain.request.BookingRequest;
import dev.kienntt.demo.BE_Vinpearl.domain.request.BookingRoomRequest;
import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.model.Room;
import dev.kienntt.demo.BE_Vinpearl.model.RoomType;
import dev.kienntt.demo.BE_Vinpearl.repository.BookingRoomRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.CustomerRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.RoomRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.RoomTypeRepository;
import dev.kienntt.demo.BE_Vinpearl.service.BookingRoomService;
import dev.kienntt.demo.BE_Vinpearl.service.EmailService;
import dev.kienntt.demo.BE_Vinpearl.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingRoomService {
    @Autowired
    private BookingRoomRepository bookingRoomRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailService emailService;

//    @Override
//    public List<BookingRoom> findAll() {
//        return bookingRoomRepository.findAll();
//    }

    LocalDateTime localDateTime = LocalDateTime.now();

    @Override
    public List<BookingRoom> findAll() {
        return (List<BookingRoom>) bookingRoomRepository.findAll();
    }

    @Override
    public Optional<BookingRoom> findById(Long id) {
        return bookingRoomRepository.findById(id);
    }

    @Override
    public BookingRoom save(BookingRoomRequest bookingRoomRequest) {
        Long roomTypeId = bookingRoomRequest.getRoomTypeId();
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("Room Type ID cannot be null."));

        Long customerId = bookingRoomRequest.getCustomerId();
        customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer ID cannot be null."));

        // Kiểm tra số phòng còn lại trong loại phòng
        if (roomType.getNumberOfRooms() <= 0) {
            new RuntimeException("No room available");
        }

        List<Room> availableRooms = roomRepository.findByRoomTypeId(roomTypeId, 0);
        Room roomRandom =  getRandomAvailableRoom(availableRooms);

        LocalDateTime dateCheckIn =
                Instant.ofEpochMilli(bookingRoomRequest.getCheckIn()).atZone(ZoneId.systemDefault()).toLocalDateTime();

        LocalDateTime dateCheckOut =
                Instant.ofEpochMilli(bookingRoomRequest.getCheckOut()).atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Tạo mới đối tượng BookingRoom và lưu vào database
        BookingRoom bookingRoom1 = new BookingRoom();
        bookingRoom1.setRoomId(roomRandom.getId());
        bookingRoom1.setCustomerId(customerId);
        bookingRoom1.setCheckIn(dateCheckIn);
        bookingRoom1.setCheckOut(dateCheckOut);

        bookingRoomRepository.save(bookingRoom1);

        // Giảm số phòng còn lại trong loại phòng
        roomType.setNumberOfRooms(roomType.getNumberOfRooms() - 1);
        roomTypeRepository.save(roomType);
        roomRandom.setStatus(1);
        roomRepository.save(roomRandom);

        // Send confirmation email
        String to = "kienntt.iist@gmail.com";
        String subject = "Booking Confirmation";
        String text = "Dear customer, your booking has been confirmed. Thank you for choosing our hotel.";
        try {
            emailService.sendEmail(to, subject, text);
        } catch (MessagingException e) {
            throw new RuntimeException("Error sending email");
        }

        return null;
    }

    @Override
    public BookingRoom update(Long bookingRoomId, BookingRoom bookingRoomDetails) {
        // Code to book hotel
        BookingRoom bookingRoom = bookingRoomRepository.findById(bookingRoomId)
                .orElseThrow(() -> new RuntimeException("Booking ID cannot be null."));

        Long roomId = bookingRoomDetails.getRoomId();
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room ID cannot be null."));

        Long customerId = bookingRoomDetails.getCustomerId();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer ID cannot be null."));

        bookingRoom.setCheckIn(bookingRoomDetails.getCheckIn());
        bookingRoom.setCheckOut(bookingRoomDetails.getCheckOut());
        bookingRoom.setPaymentDate(bookingRoomDetails.getPaymentDate());
        bookingRoom.setPaymentAmount(bookingRoomDetails.getPaymentAmount());
        bookingRoom.setPaymentStatus(bookingRoomDetails.getPaymentStatus());
        bookingRoom.setNumberParent(bookingRoomDetails.getNumberParent());
        bookingRoom.setNumberChildren(bookingRoomDetails.getNumberChildren());
        bookingRoom.setDescription(bookingRoomDetails.getDescription());
        bookingRoom.setPerNight(bookingRoomDetails.getPerNight());
        bookingRoom.setRoom(room);
        bookingRoom.setCustomer(customer);
        bookingRoom.setService(bookingRoomDetails.getService());

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

    @Override
    public Long findAllByMonth(Long startMonth, Long endMonth) {
        return bookingRoomRepository.findAllByMonth(startMonth, endMonth);
    }

    @Override
    public BookingRoom checkOutRoom(Long id) {
        BookingRoom bookingRoom = bookingRoomRepository.findById(id).orElse(null);
        if (bookingRoom == null) {
            throw new RuntimeException("Không tìm thấy đặt phòng có id này");
        }

        // Kiểm tra phòng đã được đặt hay chưa
        Long roomId = bookingRoom.getRoomId();
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.get().getStatus() == 0) {
            throw new RuntimeException("Phòng đã được đặt");
        }

        // Kiểm tra số lượng khách hợp lệ
        Long numberOfGuests = bookingRoom.getNumberParent();

        // Tính toán số tiền phải thanh toán
        bookingRoomRepository.save(bookingRoom);
        room.get().setStatus(0);
        roomRepository.save(room.get());

        System.out.println("Thanh toán thành công. Tổng số tiền phải thanh toán là " + bookingRoom.getPaymentAmount() + " đồng.");

        return null;
    }

//    private Long calculateTotalPrice(BookingRoom bookingRoom) {
//        // Tính tổng giá tiền của đặt phòng dựa trên giá tiền của loại phòng và số ngày đặt phòng
//        Long roomPriceVnd = bookingRoom.getRoom().getRoomTypes().getPriceVnd();
//        Long roomPriceUsd = bookingRoom.getRoom().getRoomTypes().getPriceUsd();
//
//        LocalDateTime localDT = LocalDateTime.of(2017, 02, 11, 8, 45, 50);
//        long localDTInMilli = localDT.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//        Long numberOfDays = (dateCheckOut - dateCheckIn) / (24 * 60 * 60 * 1000);
//        return roomPrice * numberOfDays;
//    }
//
//    private String generateInvoice(BookingRoom bookingRoom) {
//        // Tạo thông tin hóa đơn thanh toán và trả về dưới dạng chuỗi
//        StringBuilder invoice = new StringBuilder();
//        invoice.append("Room number: ").append(bookingRoom.getRoom().getRoomNumber()).append("\n");
//        invoice.append("Checkin date: ").append(bookingRoom.getCheckinDate()).append("\n");
//        invoice.append("Checkout date: ").append(bookingRoom.getCheckoutDate()).append("\n");
//        invoice.append("Total price: ").append(bookingRoom.getTotalPrice()).append("\n");
//        return invoice.toString();
//    }

    public Room getRandomAvailableRoom(List<Room> availableRooms) {
        if (availableRooms.isEmpty()) {
            // Nếu không có phòng nào còn trống, trả về null.
            return null;
        }

        int randomIndex = new Random().nextInt(availableRooms.size());
        return availableRooms.get(randomIndex);
    }
}
