package com.barber.BarberSystem.dto;

import com.barber.BarberSystem.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    //address
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String postalCode;

    // position (Client. Employee or admin)
    private UserRole role;
}
