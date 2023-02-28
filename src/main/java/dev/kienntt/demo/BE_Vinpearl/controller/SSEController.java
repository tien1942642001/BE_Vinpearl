package dev.kienntt.demo.BE_Vinpearl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.kienntt.demo.BE_Vinpearl.model.BookingRoom;
import dev.kienntt.demo.BE_Vinpearl.repository.BookingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class SSEController {
    @Autowired
    private BookingRoomRepository bookingRoomRepository;

//    @GetMapping("/upcoming-checkout-rooms")
//    public SseEmitter getUpcomingCheckoutRooms() {
//        SseEmitter emitter = new SseEmitter();
//
//        // Thực hiện kiểm tra thời gian sắp đến hạn checkout phòng
//        // Nếu thời gian sắp đến, gửi SSE event tới client
//        new Thread(() -> {
//            while (true) {
//                try {
//                    // Lấy thông tin về thời gian sắp đến hạn checkout phòng
//                    LocalDateTime checkoutTime = ;// Xử lý lấy thông tin checkout time ở đây
//
//                            // Tính thời gian còn lại đến hạn checkout
//                            Duration remainingTime = Duration.between(LocalDateTime.now(), checkoutTime);
//
//                    // Nếu thời gian còn lại nhỏ hơn 60 phút, gửi SSE event tới client
//                    if (remainingTime.toMinutes() <= 60) {
//                        String message = "Sắp đến hạn checkout phòng. Còn lại " + remainingTime.toMinutes() + " phút";
//                        emitter.send(SseEmitter.event().data(message));
//                    }
//
//                    // Sleep 1 phút để kiểm tra lại
//                    Thread.sleep(60000);
//                } catch (Exception e) {
//                    emitter.completeWithError(e);
//                    return;
//                }
//            }
//        }).start();
//
//        return emitter;
//    }
}
