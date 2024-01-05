package dev.tienvv.demo.BE_Vinpearl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomDto {
    private Long id;

    private String name;

    private String numberRoom;

    private String roomTypeName;

    private Long status;
}
