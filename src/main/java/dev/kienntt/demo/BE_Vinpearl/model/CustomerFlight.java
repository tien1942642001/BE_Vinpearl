package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer_flight")
@Getter
@Setter
public class CustomerFlight extends BaseEntity {
    private String name;

    private String phone;

    private String cccd;
}
