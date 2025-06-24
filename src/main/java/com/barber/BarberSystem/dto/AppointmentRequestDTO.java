package com.barber.BarberSystem.dto;

import com.barber.BarberSystem.model.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDTO {
    private Long clientId;
    private Long employeeId;
    private Service serviceType;
    private LocalDateTime dateTime;
}
