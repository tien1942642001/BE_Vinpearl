package dev.kienntt.demo.BE_Vinpearl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TourDto {
    private Long id;
    private String code;

    private String name;

    private String leavingTo;

    private Long priceMin;

    private Long lengthStayId;

    private String path;

    private Long numberOfPeople;

    private Long remainingOfPeople;

    private Long typeOfTourId;

    private LocalDateTime expirationDate;

    private String leavingFrom;

    private Long suitableId;
}
