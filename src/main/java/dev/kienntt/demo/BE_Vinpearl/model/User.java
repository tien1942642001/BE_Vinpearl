package dev.kienntt.demo.BE_Vinpearl.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends BaseEntity{
    @Column(name = "email")
    private String username;

    private String password;

    private String fullName;

    private String phone;

    private String cccd;

    private Long createdDateCccd;

    private Long expiredDateCccd;

    private Long sex;

    private String nationality;

    private Long district;

    private Long wards;

    private Long hotelId;

    private Long roleId;

    private String token;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "buildingId", insertable = false, updatable = false) //thông qua khóa ngoại site_id
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Building building;

}
