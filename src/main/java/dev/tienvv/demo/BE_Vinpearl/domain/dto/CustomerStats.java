package dev.tienvv.demo.BE_Vinpearl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerStats {
    private long totalCustomers;
    private long newCustomers;
    private long oldCustomers;
}
