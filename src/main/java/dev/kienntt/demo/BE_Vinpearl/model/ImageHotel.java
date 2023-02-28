package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image_hotel")
@Getter
@Setter
public class ImageHotel extends BaseEntity {
    private Long hotelId;

    private String name;

    private String path;

    @ManyToOne
    @JoinColumn(name = "hotelId", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Hotel hotel;
}
