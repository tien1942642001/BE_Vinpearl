package dev.tienvv.demo.BE_Vinpearl.model;

import dev.tienvv.demo.BE_Vinpearl.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Transient
    private Long birthDateLong;

    @Column(name = "sex")
    private Long sex;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "site_id")
    private Long siteId;

    @Column(name = "role_id")
    private Long roleId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Hotel hotel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "site_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Site site;

    @Column(name = "token")
    private String token;

}
