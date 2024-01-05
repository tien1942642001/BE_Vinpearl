package dev.tienvv.demo.BE_Vinpearl.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "service_description")
@Getter
@Setter
public class ServiceDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "description_id")
    private Long descriptionId;
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false, insertable = false, updatable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "description_id", nullable = false, insertable = false, updatable = false)
    private Description description;
}
