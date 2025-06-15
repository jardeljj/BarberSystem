package com.barber.BarberSystem.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Service service;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status; // AGENDADO, CANCELADO, CONCLUIDO

}
