package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "site")
public class Site extends BaseEntity {
    private String name;

//    @OneToMany(mappedBy = "site")
//    private Set<Hotel> hotels;
}
