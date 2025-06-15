package com.barber.BarberSystem.repository;

import com.barber.BarberSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
