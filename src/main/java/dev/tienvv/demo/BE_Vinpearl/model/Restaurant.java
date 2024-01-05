package dev.tienvv.demo.BE_Vinpearl.model;

import dev.tienvv.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
public class Restaurant extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "morning")
    private String morning;

    @Column(name = "afternoon")
    private String afternoon;

    // @Column(name = "email")
    // private Long numberPerson;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "cuisine_type")
    private String cuisineType;

    @Column(name = "hotel_id")
    private Long hotelId;
}
