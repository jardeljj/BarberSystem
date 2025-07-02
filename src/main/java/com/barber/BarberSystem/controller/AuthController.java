package com.barber.BarberSystem.controller;

import com.barber.BarberSystem.Config.AuthenticationService;
import com.barber.BarberSystem.dto.LoginRequest;
import com.barber.BarberSystem.dto.LoginResponse;
import com.barber.BarberSystem.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}
