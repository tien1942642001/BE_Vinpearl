package dev.tienvv.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.tienvv.demo.BE_Vinpearl.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "image_tour")
public class ImageTour extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "tour_id")
    private Long tourId;

    @ManyToOne
    @JoinColumn(name = "tour_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Tour tour;
}
