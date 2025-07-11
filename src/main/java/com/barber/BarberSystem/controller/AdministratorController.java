package com.barber.BarberSystem.controller;

import com.barber.BarberSystem.dto.AdministratorRequestDTO;
import com.barber.BarberSystem.dto.AdministratorResponseDTO;
import com.barber.BarberSystem.exception.ResourceNotFoundException;
import com.barber.BarberSystem.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrators")
@RequiredArgsConstructor
public class AdministratorController {

    private final AdministratorService administratorService;

    @PostMapping
    public ResponseEntity<AdministratorResponseDTO> createAdministrator(@RequestBody AdministratorRequestDTO dto){
        AdministratorResponseDTO created = administratorService.createAdministrator(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdministratorResponseDTO>> getAllAdministrator(){
        return ResponseEntity.ok(administratorService.getAllAdministrators());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorResponseDTO> getAdministratorById(@PathVariable Long id){
        return administratorService.getAdministratorById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found with id: " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministratorResponseDTO> updateAdministrator(@PathVariable Long id,
                                                                        @RequestBody AdministratorRequestDTO dto) {
        return administratorService.updateAdministrator(id, dto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found with id: " + id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable Long id) {
        administratorService.deleteAdministrator(id);
        return ResponseEntity.noContent().build();
    }
}
