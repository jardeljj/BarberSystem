package com.barber.BarberSystem.Config;

import com.barber.BarberSystem.model.Administrator;
import com.barber.BarberSystem.model.Client;
import com.barber.BarberSystem.repository.AdministratorRepository;
import com.barber.BarberSystem.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final ClientRepository clientRepository;
    private final AdministratorRepository administratorRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            Client client = clientRepository.findByEmail(email).orElse(null);
            if (client != null) return client;

            Administrator admin = administratorRepository.findByEmail(email).orElse(null);
            if (admin != null) return admin;

            throw new UsernameNotFoundException("User not found");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
