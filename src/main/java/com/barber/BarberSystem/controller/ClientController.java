package com.barber.BarberSystem.controller;

import com.barber.BarberSystem.dto.ClientRequestDTO;
import com.barber.BarberSystem.dto.ClientResponseDTO;
import com.barber.BarberSystem.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    // create a new client
    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO dto){
        ClientResponseDTO createdClient = clientService.createClient(dto);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    // search all clients
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClients(){
        List<ClientResponseDTO> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    // search clients by id
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new com.barber.BarberSystem.exception.ResourceNotFoundException("Client not found with id: " + id));
    }

    // updating a clint by id
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id, @RequestBody ClientRequestDTO dto) {
        return clientService.updateClient(id, dto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new com.barber.BarberSystem.exception.ResourceNotFoundException("Client not found with id: " + id));
    }

    // deleting a client by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
