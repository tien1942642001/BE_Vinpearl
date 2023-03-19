package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.config.VnPayConfig;
import dev.kienntt.demo.BE_Vinpearl.config.VnPayUtils;
import dev.kienntt.demo.BE_Vinpearl.domain.dto.RoomTypeMinDto;
import dev.kienntt.demo.BE_Vinpearl.domain.request.BookingRoomRequest;
import dev.kienntt.demo.BE_Vinpearl.model.*;
import dev.kienntt.demo.BE_Vinpearl.repository.BookingTourRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.CustomerRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.RoomRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.RoomTypeRepository;
import dev.kienntt.demo.BE_Vinpearl.service.BookingTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingTourServiceImpl implements BookingTourService {
    private static final String PAYMENT_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    private static final String QUERY_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";

    @Autowired
    private VnPayConfig vnPayConfig;

    @Value("${vnpay.tmncode}")
    private String tmnCode;

    @Value("${vnpay.hashsecret}")
    private String hashSecret;
    @Autowired
    private BookingTourRepository bookingTourRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Iterable findAll() {
        return bookingTourRepository.findAll();
    }

    @Override
    public Optional<BookingTour> findById(Long id) {
        return bookingTourRepository.findById(id);
    }

    @Override
    public List<BookingTour> findByCustomerId(Long id) {
        return bookingTourRepository.findByCustomerId(id);
    }

    @Override
    public BookingTour save(BookingTour bookingTour) {
        return bookingTourRepository.save(bookingTour);
    }

    @Override
    public void remove(Long id) {
        bookingTourRepository.deleteById(id);
    }

    public List<Customer> getTop5Customer() {
        Date startDate = Date.valueOf(LocalDate.now().withDayOfYear(1)); // ngày đầu tiên của năm hiện tại
        Date endDate = Date.valueOf(LocalDate.now().withDayOfYear(1).plusYears(1)); // ngày cuối cùng của năm hiện tại

        List<BookingTour> bookingTours = bookingTourRepository.findByBookingDateBetween(startDate, endDate);
        Map<Long, Integer> bookingCountMap = new HashMap<>();
        for (BookingTour bookingTour : bookingTours) {
            Long customerId = bookingTour.getCustomerId();
            if (bookingCountMap.containsKey(customerId)) {
                int bookingCount = bookingCountMap.get(customerId);
                bookingCountMap.put(customerId, bookingCount + 1);
            } else {
                bookingCountMap.put(customerId, 1);
            }
        }
        List<Map.Entry<Long, Integer>> sortedBookingCounts = new ArrayList<>(bookingCountMap.entrySet());
        Collections.sort(sortedBookingCounts, (a, b) -> b.getValue().compareTo(a.getValue()));

        List<Customer> top5Customers = new ArrayList<>();
        for (int i = 0; i < 5 && i < sortedBookingCounts.size(); i++) {
            Long customerId = sortedBookingCounts.get(i).getKey();
            Customer customer = customerRepository.findById(customerId).orElse(null);
            if (customer != null) {
                top5Customers.add(customer);
            }
        }
        return top5Customers;
    }

    public List<Tour> getTopTours(int limit) {
        // truy vấn dữ liệu từ bookingTourRepository
        List<BookingTour> bookingTours = (List<BookingTour>) bookingTourRepository.findAll();

        // tạo một Map để đếm số lần đặt tour cho mỗi tour
        Map<Long, Integer> tourCountMap = new HashMap<>();
        for (BookingTour bookingTour : bookingTours) {
            Long tourId = bookingTour.getTourId();
            if (tourCountMap.containsKey(tourId)) {
                tourCountMap.put(tourId, tourCountMap.get(tourId) + 1);
            } else {
                tourCountMap.put(tourId, 1);
            }
        }

        // sắp xếp Map theo giá trị giảm dần
        List<Map.Entry<Long, Integer>> sortedList = new ArrayList<>(tourCountMap.entrySet());
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // trả về danh sách các tour được đặt nhiều nhất
        List<Tour> topTours = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : sortedList) {
            Long tourId = entry.getKey();
//            Tour tour = bookingTours.get(0).getTourId(); // lấy thông tin tour từ bất kỳ bookingTour có chứa tourId
//            topTours.add(tour);
//            if (topTours.size() == limit) {
//                break;
//            }
        }

        return topTours;
    }

    public Map<String, Long> getBookingTourCountByMonth() {
        List<BookingTour> bookingTours = bookingTourRepository.findAllBookingTour();
        Map<String, Long> bookingTourCountByMonth = bookingTours.stream()
                .collect(Collectors.groupingBy(
                        bookingTour -> String.format("Tháng %01d", bookingTour.getPaymentDate().getMonthValue()),
                        Collectors.counting()
                ));
        return bookingTourCountByMonth;
    }

    @Override
    public Page<BookingTour> searchBookingTour(Long customerId, String code, Long stauts, Long startTime, Long endTime, Pageable pageable) {
//        PageRequest page_req = new PageRequest(0, buildingId, Sort.Direction.DESC, "idNode");
        return bookingTourRepository.searchBookingTour(customerId, code, stauts, startTime, endTime, pageable);
    }

    @Override
    public BookingTour saveBookingTour(BookingTour bookingTour) throws UnsupportedEncodingException {
        RoomTypeMinDto roomType = roomTypeRepository.findRoomTypeByMinPrice(bookingTour.getHotelId());
        Long roomTypeId = roomType.getId();
        RoomType roomType1 = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại phòng."));

        Long customerId = bookingTour.getCustomerId();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));

        // Kiểm tra số phòng còn lại trong loại phòng
        if (roomType1.getRemainingOfRooms() <= 0) {
            new RuntimeException("Đã hết phòng");
        }

        List<Room> availableRooms = roomRepository.findByRoomTypeId(roomTypeId, 0);
        if (availableRooms.isEmpty()) {
            new RuntimeException("No room available");
        }

        Room roomRandom =  getRandomAvailableRoom(availableRooms);
        String paymentDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        // Tạo mới đối tượng BookingRoom và lưu vào database
        BookingTour bookingTour1 = new BookingTour();
        bookingTour1.setRoomId(roomRandom.getId());
        bookingTour1.setCustomerId(customerId);
        bookingTour1.setHotelId(bookingTour.getHotelId());
        bookingTour1.setTourId(bookingTour.getTourId());
        bookingTour1.setPaymentAmount(bookingTour.getPaymentAmount());
        bookingTour1.setPaymentDate(bookingTour.getPaymentDate());
        bookingTour1.setDescription(bookingTour.getDescription());
        bookingTour1.setNumberAdult(bookingTour.getNumberAdult());
        bookingTour1.setNumberChildren(bookingTour.getNumberChildren());
        bookingTour1.setPaymentStatus(0L);

        BookingTour bookingTour2 = bookingTourRepository.save(bookingTour1);

        bookingTour2.setCode(String.format("VPT-TKGVB%06d", bookingTour1.getId()));
        bookingTourRepository.save(bookingTour2);

        String paymentUrl = createPaymentUrl(bookingTour2);
        if (paymentUrl.isEmpty()) {
            return null;
        }
        bookingTour2.setUrl(paymentUrl);

        return bookingTour2;
    }

    public String createPaymentUrl(BookingTour bookingTour) throws UnsupportedEncodingException {
        String vnp_Returnurl = vnPayConfig.getReturnUrl();
        String vnp_TmnCode = vnPayConfig.getTmnCode();
        String vnp_HashSecret = vnPayConfig.getHashSecret();
        String vnp_Url = vnPayConfig.getUrl();
        String vnp_Version = vnPayConfig.getVersion();
        String vnp_Command = vnPayConfig.getCommand();
//        String vnp_TxnRef = VnPayUtils.getRandomNumber(8);
        String vnp_OrderInfo = bookingTour.getDescription();
        long vnp_Amount = bookingTour.getPaymentAmount() * 100;
//        String vnp_IpAddr = bookingRoomRequest.getIp();
        String vnp_IpAddr = "192.168.100.3";
        String vnp_CurrCode = "VND";
        String vnp_Locale = "vn";
        String vnp_TxnTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Locale", vnp_Locale);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(vnp_Amount));
        vnp_Params.put("vnp_CurrCode", vnp_CurrCode);
        vnp_Params.put("vnp_TxnRef", UUID.randomUUID().toString().replace("-", ""));
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_ReturnUrl", vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_CreateDate", vnp_TxnTime);

        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();

        String vnp_SecureHash = VnPayUtils.hmacSHA512(vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = vnp_Url + "?" + queryUrl;

        if (paymentUrl.isEmpty()) {

        }
        return paymentUrl;
    }

    public Room getRandomAvailableRoom(List<Room> availableRooms) {
        if (availableRooms.isEmpty()) {
            // Nếu không có phòng nào còn trống, trả về null.
            return null;
        }

        int randomIndex = new Random().nextInt(availableRooms.size());
        return availableRooms.get(randomIndex);
    }
}
