package dev.kienntt.demo.BE_Vinpearl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageHotelDto {
    private Long id;

    private String name;

    private String path;

}
