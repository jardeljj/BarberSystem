package com.barber.BarberSystem.service;

import com.barber.BarberSystem.dto.AddressDTO;
import com.barber.BarberSystem.dto.EmployeeRequestDTO;
import com.barber.BarberSystem.dto.EmployeeResponseDTO;
import com.barber.BarberSystem.exception.ResourceNotFoundException;
import com.barber.BarberSystem.model.Employee;
import com.barber.BarberSystem.model.UserRole;
import com.barber.BarberSystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto){
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setRole(UserRole.EMPLOYEE);
        employee.setPosition(dto.getPosition());

        mapAddressFromDTO(employee, dto.getAddress());

        Employee saved = employeeRepository.save(employee);
        return toResponseDTO(saved);
    }

    public List<EmployeeResponseDTO> getAllEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeResponseDTO> getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public Optional<EmployeeResponseDTO> updateEmployee(Long id, EmployeeRequestDTO dto){
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(dto.getName());
            employee.setEmail(dto.getEmail());
            employee.setPassword(passwordEncoder.encode(dto.getPassword()));
            employee.setPhoneNumber(dto.getPhoneNumber());
            employee.setPosition(dto.getPosition());
            mapAddressFromDTO(employee, dto.getAddress());

            Employee updated = employeeRepository.save(employee);
            return toResponseDTO(updated);
        });
    }

    public void deleteEmployee(Long id){
        if(!employeeRepository.existsById(id)){
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    // --- Mappers Address ---
    private void mapAddressFromDTO(Employee employee, AddressDTO addressDTO) {
        if (addressDTO != null) {
            employee.setStreet(addressDTO.getStreet());
            employee.setNumber(addressDTO.getNumber());
            employee.setComplement(addressDTO.getComplement());
            employee.setCity(addressDTO.getCity());
            employee.setState(addressDTO.getState());
            employee.setPostalCode(addressDTO.getZipCode());
        }
    }

    private AddressDTO mapAddressToDTO(Employee employee) {
        AddressDTO dto = new AddressDTO();
        dto.setStreet(employee.getStreet());
        dto.setNumber(employee.getNumber());
        dto.setComplement(employee.getComplement());
        dto.setCity(employee.getCity());
        dto.setState(employee.getState());
        dto.setZipCode(employee.getPostalCode());
        return dto;
    }

    private EmployeeResponseDTO toResponseDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setPosition(employee.getPosition());
        dto.setAddress(mapAddressToDTO(employee));
        return dto;
    }

}
