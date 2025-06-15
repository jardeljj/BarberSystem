package com.barber.BarberSystem.repository;

import com.barber.BarberSystem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
