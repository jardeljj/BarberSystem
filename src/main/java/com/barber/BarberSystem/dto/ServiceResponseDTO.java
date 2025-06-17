package com.barber.BarberSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseDTO {

    private Long id;
    private String name;
    private String description;
    private double price;

}
