package com.barber.BarberSystem.repository;

import com.barber.BarberSystem.model.Administrator;
import com.barber.BarberSystem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Optional<Administrator> findByEmail(String email);
}
