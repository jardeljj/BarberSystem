package com.barber.BarberSystem.dto;

import com.barber.BarberSystem.model.AppointmentStatus;
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
    private String serviceName;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;

}
