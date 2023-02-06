package dev.kienntt.demo.BE_Vinpearl.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "extension")
@Getter
@Setter
public class Extension extends BaseEntity {
    private String name;
}
