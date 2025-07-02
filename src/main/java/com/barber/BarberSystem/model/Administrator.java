package com.barber.BarberSystem.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Administrator extends User {

    // Pode não ter campos extras, só permissões maiores

}
