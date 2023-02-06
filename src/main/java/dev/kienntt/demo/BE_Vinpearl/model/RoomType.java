package dev.kienntt.demo.BE_Vinpearl.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room_type")
@Getter
@Setter
public class RoomType extends BaseEntity{
    private String name;

    private Long area;

    private Long numberParent;

    private Long numberChildren;

    private String description;

    private Long price;

    private Long hotelId;

    @OneToMany(mappedBy = "roomTypeId", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Room> rooms;
}
