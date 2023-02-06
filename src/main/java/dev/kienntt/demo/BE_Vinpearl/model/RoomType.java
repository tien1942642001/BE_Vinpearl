package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToMany(mappedBy = "roomType", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties("roomType")
    private Set<Room> rooms;
}
