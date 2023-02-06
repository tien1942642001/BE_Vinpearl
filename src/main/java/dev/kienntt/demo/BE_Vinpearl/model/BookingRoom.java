package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BookingRoom extends BaseEntity {
    private Long startTime;

    private Long endTime;

    private Long paymentDate;

    private Long paymentAmount;

    private Long paymentStatus;

    private Long numberParent;

    private Long numberChildren;

    private String description;

    private Long perNight;

    private Long userId;

    private Long roomId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "roomId", nullable = false, insertable = false, updatable = false)
//    private Room room;
}
