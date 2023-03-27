package dev.kienntt.demo.BE_Vinpearl.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class SearchExportRequest {
    private LocalDate startDate;

    private LocalDate endDate;

    private Long status;

}
