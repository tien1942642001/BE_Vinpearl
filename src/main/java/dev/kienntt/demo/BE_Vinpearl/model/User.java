package dev.kienntt.demo.BE_Vinpearl.model;

import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseEntity {
    private String email;

    private String password;

    private String fullName;

    private String phone;

    private LocalDateTime birthDate;

    private Long sex;

    private Long hotelId;

    private Long roleId;

    private String token;

}
