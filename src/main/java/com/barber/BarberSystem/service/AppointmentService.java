package com.barber.BarberSystem.service;

import com.barber.BarberSystem.dto.AppointmentRequestDTO;
import com.barber.BarberSystem.dto.AppointmentResponseDTO;
import com.barber.BarberSystem.exception.ResourceNotFoundException;
import com.barber.BarberSystem.model.Appointment;
import com.barber.BarberSystem.model.Client;
import com.barber.BarberSystem.model.Employee;
import com.barber.BarberSystem.repository.AppointmentRepository;
import com.barber.BarberSystem.repository.ClientRepository;
import com.barber.BarberSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    // create a appointment
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO dto){
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + dto.getClientId()));

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + dto.getEmployeeId()));

        Appointment appointment = new Appointment();
        appointment.setClient(client);
        appointment.setEmployee(employee);
        appointment.setDateTime(dto.getDateTime());
        appointment.setService(dto.getServiceType());

        Appointment saved = appointmentRepository.save(appointment);
        return toResponseDTO(saved);
    }

    // search all appointments
    public List<AppointmentResponseDTO> getAllAppointments(){
        return appointmentRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // search appointments by id
    public Optional<AppointmentResponseDTO> getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(this::toResponseDTO);
    }

    // update a appointment with id
    public Optional<AppointmentResponseDTO> updateAppointment(Long id, AppointmentRequestDTO dto){
        return appointmentRepository.findById(id).map(appointment -> {
            Client client = clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + dto.getClientId()));

            Employee employee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + dto.getEmployeeId()));

            appointment.setClient(client);
            appointment.setEmployee(employee);
            appointment.setDateTime(dto.getDateTime());
            appointment.setService(dto.getServiceType());

            Appointment updated = appointmentRepository.save(appointment);
            return toResponseDTO(updated);
        });
    }

    public void deleteAppointment(Long id){
        if (!appointmentRepository.existsById(id)){
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }


    private AppointmentResponseDTO toResponseDTO(Appointment appointment) {
        AppointmentResponseDTO dto = new AppointmentResponseDTO();
        dto.setId(appointment.getId());
        dto.setClientId(appointment.getClient().getId());
        dto.setEmployeeId(appointment.getEmployee().getId());
        dto.setDateTime(appointment.getDateTime());
        dto.setServiceType(appointment.getService());
        return dto;
    }

}
