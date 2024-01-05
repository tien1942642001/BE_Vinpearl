package dev.tienvv.demo.BE_Vinpearl.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "service_content")
@Getter
@Setter
public class ServiceContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "content_id")
    private Long contentId;
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false, insertable = false, updatable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "content_id", nullable = false, insertable = false, updatable = false)
    private Content content;
}
