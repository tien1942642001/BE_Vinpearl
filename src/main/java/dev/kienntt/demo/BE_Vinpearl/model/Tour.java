package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tour")
public class Tour extends BaseEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

//    private LocalDateTime startTime;
//
//    private LocalDateTime endTime;
    @Column(name = "description")
    private String description;

    @Column(name = "inclusion")
    private String inclusion;

    @Column(name = "terms_conditions")
    private String termsConditions;

    @Column(name = "leaving_from_id")
    private Long leavingFromId;

    @Column(name = "leaving_to_id")
    private Long leavingToId;

    @Column(name = "length_stay_id")
    private Long lengthStayId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "leaving_from_id", referencedColumnName = "id", insertable = false, updatable = false, nullable=false)
    private Site leavingFrom;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "leaving_to_id", referencedColumnName = "id", insertable = false, updatable = false, nullable=false)
    private Site leavingTo;

    @Column(name = "suitable_id")
    private Long suitableId;

    @Column(name = "status")
    private Long status;

    @Column(name = "type_of_tour_id")
    private Long typeOfTourId;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "number_of_people")
    private Long numberOfPeople;

    @Transient
    private Long expirationDateMls;

    @Transient
    private Long price;

//    @OneToOne()
//    @JoinColumn(name = "siteId", referencedColumnName = "id", insertable = false, updatable = false)
//    private Site site;

    @OneToMany(mappedBy = "tourId", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties("hotel")
    private Set<ImageTour> images;

//    @ManyToMany(fetch = FetchType.EAGER,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(
//            name = "tour_hotel",
//            joinColumns = @JoinColumn(name = "tour_id"),
//            inverseJoinColumns = @JoinColumn(name = "hotel_id"))
//    Set<Hotel> hotels;
}
