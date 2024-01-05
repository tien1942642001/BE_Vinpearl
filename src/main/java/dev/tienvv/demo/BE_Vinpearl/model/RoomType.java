package dev.tienvv.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.tienvv.demo.BE_Vinpearl.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room_type")
@Getter
@Setter
public class RoomType extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "acreage")
    private Long acreage;

    @Column(name = "number_adult")
    private Long numberAdult;

    @Column(name = "number_children")
    private Long numberChildren;

    @Column(name = "description")
    private String description;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "number_of_rooms")
    private Long numberOfRooms;

    @Column(name = "remaining_of_rooms")
    private Long remainingOfRooms;

    @OneToMany(mappedBy = "roomTypeId", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties("roomType")
    private Set<ImageRoomType> images;

    //    @OneToMany(mappedBy = "roomTypes", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "roomTypes", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<Room> rooms;

    @OneToMany(mappedBy = "roomType", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties("roomType")
    private Set<Service> service;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false, insertable = false, updatable = false)
    // @JsonIgnore
    private Hotel hotel;
}
