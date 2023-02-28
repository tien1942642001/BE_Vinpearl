package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
@Getter
@Setter
@NoArgsConstructor
public class Room extends BaseEntity {
    private String name;

    private Long numberRoom;

    private Long gala;

    private Long cocktail;

    private Long roomGroupType;

    private Long roomTypeId;

//    @OneToMany(mappedBy = "roomId", fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("roomType")
//    private List<BookingRoom> bookingRooms;

    @ManyToOne()
    @JoinColumn(name = "roomTypeId", nullable = false, insertable = false, updatable = false)
    @JsonIgnoreProperties("roomTypes")
    private RoomType roomTypes;

    private Integer status;
}
