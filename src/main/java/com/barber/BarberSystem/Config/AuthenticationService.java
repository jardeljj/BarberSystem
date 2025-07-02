package com.barber.BarberSystem.Config;

import com.barber.BarberSystem.dto.LoginRequest;
import com.barber.BarberSystem.dto.LoginResponse;
import com.barber.BarberSystem.dto.RegisterRequest;
import com.barber.BarberSystem.model.Administrator;
import com.barber.BarberSystem.model.Client;
import com.barber.BarberSystem.model.Employee;
import com.barber.BarberSystem.model.User;
import com.barber.BarberSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
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
            // Busca o usuário pelo email
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Gera o token JWT
            String jwtToken = jwtService.generateToken(user);
            return new LoginResponse(jwtToken);

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
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());

        user.setStreet(request.getStreet());
        user.setNumber(request.getNumber());
        user.setComplement(request.getComplement());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setPostalCode(request.getPostalCode());

        user.setRole(request.getRole());
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return new LoginResponse(jwtToken);

    }

}
