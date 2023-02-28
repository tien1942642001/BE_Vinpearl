package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
@Getter
@Setter
public class Airport extends BaseEntity {
    private String name;

    private Long siteId;

    @ManyToOne
    @JoinColumn(name="siteId", nullable=false, insertable = false, updatable = false)
    private Site site;
}
