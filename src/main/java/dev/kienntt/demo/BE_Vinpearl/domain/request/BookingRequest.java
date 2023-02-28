package dev.kienntt.demo.BE_Vinpearl.domain.request;

import dev.kienntt.demo.BE_Vinpearl.model.Customer;

import java.time.LocalDate;

public class BookingRequest {
    private Long roomId;

    private Long roomTypeId;

    private Customer customer;

    private LocalDate checkIn;

    private LocalDate checkOut;
}
