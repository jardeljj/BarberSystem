package com.barber.BarberSystem.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee extends User{
    private String position;
    // Ex: turno, etc. (se necessário futuramente)

}
