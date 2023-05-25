package dev.kienntt.demo.BE_Vinpearl.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingTourStatistic {
    private String month;
    private long dataTour;

    private long dataRoom;
}
