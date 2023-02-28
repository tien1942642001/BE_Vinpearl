package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer extends BaseEntity {
    private String phone;

    private String cccd;

    private LocalDateTime createdDateCccd;

    private LocalDateTime expiredDateCccd;

    private LocalDateTime birthDate;

    private Integer sex;

    private String email;

    private String fullName;

    private String password;

    private String nationality;

    private String wards;

}
