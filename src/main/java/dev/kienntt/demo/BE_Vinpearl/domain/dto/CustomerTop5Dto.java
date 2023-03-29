package dev.kienntt.demo.BE_Vinpearl.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerTop5Dto {
    private String fullName;

    private Integer sex;

    private String email;

    private String phone;

    private Long total;
}
