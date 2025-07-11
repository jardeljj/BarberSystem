package com.barber.BarberSystem.service;

import com.barber.BarberSystem.dto.AddressDTO;
import com.barber.BarberSystem.dto.AdministratorRequestDTO;
import com.barber.BarberSystem.dto.AdministratorResponseDTO;
import com.barber.BarberSystem.exception.ResourceNotFoundException;
import com.barber.BarberSystem.model.Administrator;
import com.barber.BarberSystem.model.UserRole;
import com.barber.BarberSystem.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministratorService {

    private final AdministratorRepository administratorRepository;

    public AdministratorResponseDTO createAdministrator(AdministratorRequestDTO dto){
        Administrator admin = new Administrator();
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setPhoneNumber(dto.getPhoneNumber());
        admin.setRole(UserRole.ADMIN);
        mapAddressToDTO(admin, dto.getAddress());

        Administrator saved = administratorRepository.save(admin);
        return toResponseDTO(saved);

    }

    public List<AdministratorResponseDTO> getAllAdministrators() {
        return administratorRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<AdministratorResponseDTO> getAdministratorById(Long id) {
        return administratorRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public Optional<AdministratorResponseDTO> updateAdministrator(Long id, AdministratorRequestDTO dto) {
        return administratorRepository.findById(id).map(admin -> {
            admin.setName(dto.getName());
            admin.setEmail(dto.getEmail());
            admin.setPassword(dto.getPassword());
            admin.setPhoneNumber(dto.getPhoneNumber());
            mapAddressFromDTO(admin, dto.getAddress());

            Administrator updated = administratorRepository.save(admin);
            return toResponseDTO(updated);
        });
    }

    public void deleteAdministrator(Long id) {
        if (!administratorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Administrator not found with id: " + id);
        }
        administratorRepository.deleteById(id);
    }

    private void mapAddressFromDTO(Administrator admin, AddressDTO addressDTO) {
        if (addressDTO != null) {
            admin.setStreet(addressDTO.getStreet());
            admin.setNumber(addressDTO.getNumber());
            admin.setComplement(addressDTO.getComplement());
            admin.setCity(addressDTO.getCity());
            admin.setState(addressDTO.getState());
            admin.setPostalCode(addressDTO.getZipCode());
        }
    }

    private AddressDTO mapAddressToDTO(Administrator admin) {
        AddressDTO dto = new AddressDTO();
        dto.setStreet(admin.getStreet());
        dto.setNumber(admin.getNumber());
        dto.setComplement(admin.getComplement());
        dto.setCity(admin.getCity());
        dto.setState(admin.getState());
        dto.setZipCode(admin.getPostalCode());
        return dto;
    }

    private AdministratorResponseDTO toResponseDTO(Administrator admin) {
        AdministratorResponseDTO dto = new AdministratorResponseDTO();
        dto.setId(admin.getId());
        dto.setName(admin.getName());
        dto.setEmail(admin.getEmail());
        dto.setPhoneNumber(admin.getPhoneNumber());
        dto.setAddress(mapAddressToDTO(admin));
        return dto;
    }

}
