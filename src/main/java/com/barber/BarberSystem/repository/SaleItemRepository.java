package com.barber.BarberSystem.repository;

import com.barber.BarberSystem.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
