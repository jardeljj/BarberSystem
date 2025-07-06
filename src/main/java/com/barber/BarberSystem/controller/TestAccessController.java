package com.barber.BarberSystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestAccessController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String onlyAdminAccess(){
        return "✅ Acesso permitido apenas para ADMIN";
    }

    @GetMapping("/employee")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String onlyEmployeeAccess() {
        return "✅ Acesso permitido apenas para EMPLOYEE";
    }

    @GetMapping("/client")
    @PreAuthorize("hasRole('CLIENT')")
    public String onlyClientAccess() {
        return "✅ Acesso permitido apenas para CLIENT";
    }

    @GetMapping("/admin-or-employee")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public String adminOrEmployeeAccess() {
        return "✅ Acesso permitido para ADMIN ou EMPLOYEE";
    }

    @GetMapping("/public")
    public String publicAccess() {
        return "🌍 Este endpoint é público e não exige token.";
    }

}
