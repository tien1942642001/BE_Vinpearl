package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "booking_room")
public class BookingRoom extends BaseEntity {
    @Column(name = "code")
    private String code;
    @Column(name = "check_in")
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    private LocalDateTime checkOut;

    @Column(name = "payment_code")
    private String paymentCode;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payment_amount")
    private Long paymentAmount;

    @Column(name = "payment_status")
    private Long paymentStatus;

    @Column(name = "number_adult")
    private Long numberAdult;

    @Column(name = "number_children")
    private Long numberChildren;

    @Column(name = "description")
    private String description;

    @Column(name = "per_night")
    private Long perNight;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Transient
    private String ip;

    @Transient
    private String url;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "room_id", nullable = false, insertable = false, updatable = false)
    private Room room;

    @ManyToOne()
    @JoinColumn(name = "service_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Service service;

    @ManyToOne()
    @JoinColumn(name = "hotel_id", nullable = false, insertable = false, updatable = false)
    private Hotel hotel;
}
