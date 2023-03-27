package dev.kienntt.demo.BE_Vinpearl.domain.request;

import dev.kienntt.demo.BE_Vinpearl.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BookingRoomRequest {
    private Long roomTypeId;

    private Long serviceId;

    private Long customerId;

    private Long hotelId;

    private Long checkIn;

    private Long checkOut;

    private Long numberAdult;

    private Long numberChildren;

    private String ip;

    private String description;

    private Long paymentAmount;

    private String paymentCode;

    private LocalDateTime paymentDate;
}
