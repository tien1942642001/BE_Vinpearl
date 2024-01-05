package dev.tienvv.demo.BE_Vinpearl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomTypeMinDto {
    private Long id;

    private String name;

    private Long priceMin;
}
