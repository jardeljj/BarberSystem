package com.barber.BarberSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

    private String name;
    private String email;
    private String password;
    private AddressDTO address;

}
