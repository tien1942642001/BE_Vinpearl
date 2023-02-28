package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotel")
@Getter
@Setter
public class Hotel extends BaseEntity {
    private String name;

    private Long area;

    private String email;

    private String description;

    private String address;

    private Long totalRoom;

    private Long totalPerson;

    private String phone;

    private Long siteId;

//    @OneToMany(mappedBy = "hotelId", fetch = FetchType.EAGER)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<RoomType> roomTypes;

//    @OneToMany(mappedBy = "hotelId", fetch = FetchType.EAGER)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<Restaurant> restaurant;

    @OneToMany(mappedBy = "hotelId", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties("hotel")
    private Set<ImageHotel> images;

    @ManyToOne
    @JoinColumn(name="siteId", nullable=false, insertable = false, updatable = false)
    private Site site;
}
