package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ticket_type")
@Getter
@Setter
public class TicketType extends BaseEntity {
    private String name;

    private Long totalSeat;

    private Long price;

}
