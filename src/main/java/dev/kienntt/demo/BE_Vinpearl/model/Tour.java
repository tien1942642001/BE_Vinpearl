package dev.kienntt.demo.BE_Vinpearl.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "tour")
public class Tour extends BaseEntity {
    private String name;

    private String path;

    private String startTime;

    private String endTime;

    private Long price;

    private String description;

    private String inclusion;

    private String termsConditions;

    private String siteId;

    @OneToOne()
    @JoinColumn(name = "siteId", referencedColumnName = "id", insertable = false, updatable = false)
    private Site site;
}
