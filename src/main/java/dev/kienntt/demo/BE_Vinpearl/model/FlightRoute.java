package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "flight_route")
@Getter
@Setter
public class FlightRoute extends BaseEntity {
    private String startFlight;

    private String endFlight;
}
