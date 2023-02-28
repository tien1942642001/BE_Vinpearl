package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "description")
@Getter
@Setter
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_vn")
    private String nameVn;

    @Column(name = "name_en")
    private String nameEn;
    @ManyToMany(mappedBy = "descriptions")
    @JsonIgnore
    private Set<Service> services = new HashSet<>();
}