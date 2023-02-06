package dev.kienntt.demo.BE_Vinpearl.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
public class Restaurant extends BaseEntity {
    private String name;

    private String morning;

    private String afternoon;

//    private Long numberPerson;

    private String phone;

    private String description;

    private String cuisineType;

    private Long hotelId;
}
