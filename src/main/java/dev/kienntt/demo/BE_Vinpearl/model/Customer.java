package dev.kienntt.demo.BE_Vinpearl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.kienntt.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer extends BaseEntity {
    @Column(name = "phone")
    private String phone;

    @Column(name = "cccd")
    private String cccd;

    @Column(name = "created_date_cccd")
    private LocalDateTime createdDateCccd;

    @Column(name = "expired_date_cccd")
    private LocalDateTime expiredDateCccd;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "email")
    private String email;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "password")
//    @JsonIgnore
    private String password;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "wards")
    private String wards;

    @Column(name = "token")
    private String token;

//    @Column(name = "is_new_customer")
//    private boolean isNewCustomer;

}
