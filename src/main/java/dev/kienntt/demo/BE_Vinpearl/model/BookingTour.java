package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "booking_tour")
public class BookingTour extends BaseEntity {
    private String code;

    private LocalDateTime paymentDate;

    private Long paymentAmount;

    private Long paymentStatus;

    private Long numberAdult;

    private Long numberChildren;

    private String description;

    private Long customerId;

    private Long tourId;

    private Long hotelId;

    private Long roomId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roomId", nullable = false, insertable = false, updatable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tourId", nullable = false, insertable = false, updatable = false)
    private Tour tour;

    @Transient
    private String url;
}
