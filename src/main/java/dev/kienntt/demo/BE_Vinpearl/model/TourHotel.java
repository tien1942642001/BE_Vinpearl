package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tour_hotel")
public class TourHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tour_id")
    private Long tourId;

    @Column(name = "hotel_id")
    private Long hotelId;
    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false, insertable = false, updatable = false)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false, insertable = false, updatable = false)
    private Hotel hotel;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    private String description;
}
