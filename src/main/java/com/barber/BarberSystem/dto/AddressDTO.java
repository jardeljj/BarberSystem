package com.barber.BarberSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String zipCode;
}
