package com.barber.BarberSystem.repository;

import com.barber.BarberSystem.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
