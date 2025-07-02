package com.barber.BarberSystem.Config;

import com.barber.BarberSystem.dto.LoginRequest;
import com.barber.BarberSystem.dto.LoginResponse;
import com.barber.BarberSystem.dto.RegisterRequest;
import com.barber.BarberSystem.model.Administrator;
import com.barber.BarberSystem.model.Client;
import com.barber.BarberSystem.model.Employee;
import com.barber.BarberSystem.model.User;
import com.barber.BarberSystem.repository.AdministratorRepository;
import com.barber.BarberSystem.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final AdministratorRepository administratorRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public LoginResponse authenticate(LoginRequest request) {
        try {
            // Autentica com base no email e senha
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            // Tenta buscar o usuário nos dois repositórios
            Optional<Client> client = clientRepository.findByEmail(request.getEmail());
            Optional<Administrator> admin = administratorRepository.findByEmail(request.getEmail());

            if (client.isPresent()) {
                String jwtToken = jwtService.generateToken(client.get());
                return new LoginResponse(jwtToken);
            } else if (admin.isPresent()) {
                String jwtToken = jwtService.generateToken(admin.get());
                return new LoginResponse(jwtToken);
            } else {
                throw new RuntimeException("User not found");
            }

        } catch (AuthenticationException ex) {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public LoginResponse register(RegisterRequest request){
        // create a new user (or Client in the future)
        User user;

        switch (request.getRole()) {
            case CLIENT -> user = new Client();
            case EMPLOYEE -> user = new Employee();
            case ADMIN -> user = new Administrator();
            default -> throw new IllegalArgumentException("Invalid role");
        }
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());

        user.setStreet(request.getStreet());
        user.setNumber(request.getNumber());
        user.setComplement(request.getComplement());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setPostalCode(request.getPostalCode());

        user.setRole(request.getRole());
        // Salvar no repositório correspondente
        switch (request.getRole()) {
            case CLIENT -> clientRepository.save((Client) user);
            case EMPLOYEE -> throw new UnsupportedOperationException("Registering employee not implemented yet");
            case ADMIN -> administratorRepository.save((Administrator) user);
        }

        String jwtToken = jwtService.generateToken(user);
        return new LoginResponse(jwtToken);

    }

}
