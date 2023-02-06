package dev.kienntt.demo.BE_Vinpearl.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "site")
public class Site extends BaseEntity{
    private String name;

//    @OneToMany(mappedBy = "site")
//    private Set<Hotel> hotels;
}
