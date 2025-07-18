package com.barber.BarberSystem.controller;

import com.barber.BarberSystem.dto.AppointmentRequestDTO;
import com.barber.BarberSystem.dto.AppointmentResponseDTO;
import com.barber.BarberSystem.exception.ResourceNotFoundException;
import com.barber.BarberSystem.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    //create a new appointment
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@RequestBody AppointmentRequestDTO dto){
        AppointmentResponseDTO created = appointmentService.createAppointment(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // search to all appointments
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAllAppointments(){
        List<AppointmentResponseDTO> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    // search to appointment by id
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable Long id){
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }

    // updating a appointment by id
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequestDTO dto){
        return appointmentService.updateAppointment(id, dto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id" + id));
    }

    // deleting a appointment by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }


}
