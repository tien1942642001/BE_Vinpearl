package dev.kienntt.demo.BE_Vinpearl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class HotelDto {
    private Long id;

    private String name;

    private String address;

    private Long priceMin;

    private String path;

    public HotelDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
