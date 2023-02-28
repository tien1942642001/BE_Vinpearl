package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "plane")
@Getter
@Setter
public class Plane extends BaseEntity {
    private String name;

    private String code;

    private Integer totalSeat;
}
