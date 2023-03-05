package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

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
    @Column(name = "description_vn")
    private String descriptionVn;

    @Column(name = "description_en")
    private String descriptionEn;

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

    @Column(name = "suitable_id")
    private Long suitableId;

    @Column(name = "price")
    private Long price;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "number_of_people")
    private Long numberOfPeople;

    @Transient
    private Long expirationDateMls;

//    @OneToOne()
//    @JoinColumn(name = "siteId", referencedColumnName = "id", insertable = false, updatable = false)
//    private Site site;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "tour_hotel",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id"))
    Set<Hotel> hotels;
}
