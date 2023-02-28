package dev.kienntt.demo.BE_Vinpearl.domain.request;

import dev.kienntt.demo.BE_Vinpearl.model.Customer;

import java.time.LocalDate;

public class BookingRoomRequest {
    private Long roomTypeId;

    private Long customerId;

    private Long checkIn;

    private Long checkOut;

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Long checkIn) {
        this.checkIn = checkIn;
    }

    public Long getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Long checkOut) {
        this.checkOut = checkOut;
    }
}
