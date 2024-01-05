package dev.tienvv.demo.BE_Vinpearl.domain.dto;

import dev.tienvv.demo.BE_Vinpearl.model.Hotel;
import dev.tienvv.demo.BE_Vinpearl.model.Tour;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TourHotelDto {
    private Long tourId;

    private String name;

//    private Tour tour;
//
//    private List<Hotel> hotels;
}
