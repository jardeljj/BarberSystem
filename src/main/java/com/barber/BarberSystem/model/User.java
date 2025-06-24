package com.barber.BarberSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;
    private String email;
    private String password;

    private String phoneNumber;

    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
