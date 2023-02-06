package dev.kienntt.demo.BE_Vinpearl.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image_room_type")
@Getter
@Setter
public class ImageRoomType extends BaseEntity {
    private Long roomTypeId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Room room;
}
