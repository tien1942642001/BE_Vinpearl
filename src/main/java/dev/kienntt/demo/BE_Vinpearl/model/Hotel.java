package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotel")
@Getter
@Setter
public class Hotel extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "acreage")
    private Long acreage;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "total_room")
    private Long totalRoom;

    @Column(name = "total_person")
    private Long totalPerson;

    @Column(name = "phone")
    private String phone;

    @Column(name = "site_id")
    private Long siteId;

    @OneToMany(mappedBy = "hotelId", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<RoomType> roomTypes;

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
    @JoinColumn(name="site_id", nullable=false, insertable = false, updatable = false)
    private Site site;
}
