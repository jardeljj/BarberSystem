package com.barber.BarberSystem.Config;

import com.barber.BarberSystem.dto.LoginRequest;
import com.barber.BarberSystem.dto.LoginResponse;
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
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Gera o token JWT
            String jwtToken = jwtService.generateToken(user);

            return new LoginResponse(jwtToken);

        } catch (AuthenticationException ex) {
            throw new RuntimeException("Invalid credentials");
        }
    }

}
