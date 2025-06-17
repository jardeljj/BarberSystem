package com.barber.BarberSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO {
    private Long clientId;
    private List<SaleItemDTO> items;
}
