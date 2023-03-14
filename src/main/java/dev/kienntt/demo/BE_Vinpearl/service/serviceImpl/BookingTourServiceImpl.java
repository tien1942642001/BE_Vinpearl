package dev.kienntt.demo.BE_Vinpearl.service.serviceImpl;

import dev.kienntt.demo.BE_Vinpearl.model.BookingTour;
import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import dev.kienntt.demo.BE_Vinpearl.model.Tour;
import dev.kienntt.demo.BE_Vinpearl.repository.BookingTourRepository;
import dev.kienntt.demo.BE_Vinpearl.repository.CustomerRepository;
import dev.kienntt.demo.BE_Vinpearl.service.BookingTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingTourServiceImpl implements BookingTourService {
    @Autowired
    private BookingTourRepository bookingTourRepository;

    @Autowired
    private CustomerRepository customerRepository;

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
}
