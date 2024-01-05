package dev.tienvv.demo.BE_Vinpearl.model;

import dev.tienvv.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;
}
