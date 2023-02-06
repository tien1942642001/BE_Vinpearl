package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

//    @OneToOne()
//    @JoinColumn(name = "roomTypeId", referencedColumnName = "id", insertable = false, updatable = false)
//    private RoomType roomType;
    @OneToMany(mappedBy = "roomId", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<BookingRoom> bookingRooms;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roomTypeId", nullable = false, insertable = false, updatable = false)
    @JsonIgnoreProperties("roomType")
    private RoomType roomType;

}
