package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room_type")
@Getter
@Setter
public class RoomType extends BaseEntity {
    private String name;

    private Long area;

    private Long numberParent;

    private Long numberChildren;

//    private Long priceVnd;
//
//    private Long priceUsd;

    private String description_vn;

    private String description_en;

    private Long hotelId;

    private Long numberOfRooms;

    @OneToMany(mappedBy = "roomTypeId", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties("roomType")
    private Set<ImageRoomType> images;

//    @OneToMany(mappedBy = "roomTypes", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "roomTypes", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties("roomTypes")
    private Set<Room> rooms;

    @OneToMany(mappedBy = "roomType", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties("roomType")
    private Set<Service> service;
}
