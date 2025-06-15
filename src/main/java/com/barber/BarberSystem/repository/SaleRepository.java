package com.barber.BarberSystem.repository;

import com.barber.BarberSystem.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
