package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@Table(name = "site")
public class Site extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Transient
    private String creator;

//    @OneToMany(mappedBy = "site")
//    private Set<Hotel> hotels;
}
