package com.barber.BarberSystem.repository;

import com.barber.BarberSystem.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
