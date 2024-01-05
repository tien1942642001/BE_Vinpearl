package dev.tienvv.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.tienvv.demo.BE_Vinpearl.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "image_hotel")
@Getter
@Setter
public class ImageHotel extends BaseEntity {
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Hotel hotel;
}
