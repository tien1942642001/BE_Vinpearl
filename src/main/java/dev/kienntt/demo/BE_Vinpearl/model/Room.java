package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "name")
    private String name;

    @Column(name = "number_room")
    private String numberRoom;

    @Column(name = "gala")
    private Long gala;

    @Column(name = "cocktail")
    private Long cocktail;

    @Column(name = "room_group_type")
    private Long roomGroupType;

    @Column(name = "room_type_id")
    private Long roomTypeId;

//    @OneToMany(mappedBy = "roomId", fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("roomType")
//    private List<BookingRoom> bookingRooms;

    @ManyToOne()
    @JoinColumn(name = "room_type_id", nullable = false, insertable = false, updatable = false)
//    @JsonIgnore
    private RoomType roomTypes;

    @Column(name = "status")
    private Long status;

    @OneToMany(mappedBy = "roomId", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<BookingRoom> bookingRoom;
}
