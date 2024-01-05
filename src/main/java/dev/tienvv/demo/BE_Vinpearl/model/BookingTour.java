package dev.tienvv.demo.BE_Vinpearl.model;

import dev.tienvv.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "booking_tour")
public class BookingTour extends BaseEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payment_amount")
    private Long paymentAmount;

    @Column(name = "payment_status")
    private Long paymentStatus;

    @Column(name = "payment_code")
    private String paymentCode;

    @Column(name = "number_adult")
    private Long numberAdult;

    @Column(name = "number_children")
    private Long numberChildren;

    @Column(name = "description")
    private String description;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "tour_id")
    private Long tourId;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "room_id")
    private Long roomId;

    @ManyToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "room_id", nullable = false, insertable = false, updatable = false)
    private Room room;

    @ManyToOne()
    @JoinColumn(name = "tour_id", nullable = false, insertable = false, updatable = false)
    private Tour tour;

//    @ManyToOne()
//    @JoinColumn(name = "hotel_id", nullable = false, insertable = false, updatable = false)
//    private Hotel hotel;

    @Transient
    private String url;
}
