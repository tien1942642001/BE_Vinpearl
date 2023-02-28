package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tour")
public class Tour extends BaseEntity {
    private String name;

    private String path;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long price;

    private String description;

    private String inclusion;

    private String termsConditions;

    private String siteId;

    private Long priceVnd;

    private Long priceUsd;

    @OneToOne()
    @JoinColumn(name = "siteId", referencedColumnName = "id", insertable = false, updatable = false)
    private Site site;
}
