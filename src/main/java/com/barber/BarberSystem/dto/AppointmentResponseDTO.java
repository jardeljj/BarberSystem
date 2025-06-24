package com.barber.BarberSystem.dto;

import com.barber.BarberSystem.model.AppointmentStatus;
import com.barber.BarberSystem.model.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDTO {
    private Long id;
    private String clientName;
    private String employeeName;
    private Long clientId;
    private Long employeeId;
    private Service serviceType;
    private LocalDateTime dateTime;
    private AppointmentStatus status;

}
