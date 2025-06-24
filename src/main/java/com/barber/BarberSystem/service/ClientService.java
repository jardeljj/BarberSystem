package com.barber.BarberSystem.service;

import com.barber.BarberSystem.dto.AddressDTO;
import com.barber.BarberSystem.dto.ClientRequestDTO;
import com.barber.BarberSystem.dto.ClientResponseDTO;
import com.barber.BarberSystem.exception.ResourceNotFoundException;
import com.barber.BarberSystem.model.Client;
import com.barber.BarberSystem.model.UserRole;
import com.barber.BarberSystem.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    // Create a new client
    public ClientResponseDTO createClient(ClientRequestDTO dto){
        Client client = new Client();
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword());
        client.setPhoneNumber(dto.getPhoneNumber());
        client.setRole(UserRole.CLIENT);

        Client savedClient = clientRepository.save(client);
        return toResponseDTO(savedClient);
    }

    // Read search a client by Id
    public Optional<ClientResponseDTO> getClientById(Long id){
        return clientRepository.findById(id)
                .map(this::toResponseDTO);
    }

    // Update a client
    public Optional<ClientResponseDTO> updateClient(Long id, ClientRequestDTO dto){
        return clientRepository.findById(id).map(client -> {
            client.setName(dto.getName());
            client.setEmail(dto.getEmail());
            client.setPassword(dto.getPassword());
            client.setPhoneNumber(dto.getPhoneNumber());
            mapAddressFromDTO(client, dto.getAddress());

            Client updated = clientRepository.save(client);
            return toResponseDTO(updated);
        });
    }

    // Delete a client
    public void deleteClient(Long id){
        if (!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Client not found");
        }
        clientRepository.deleteById(id);
    }


    //  Métodos de mapeamento AddressDTO <-> Campos da entidade

    private void mapAddressFromDTO(Client client, AddressDTO addressDTO) {
        if (addressDTO != null) {
            client.setStreet(addressDTO.getStreet());
            client.setNumber(addressDTO.getNumber());
            client.setComplement(addressDTO.getComplement());
            client.setCity(addressDTO.getCity());
            client.setState(addressDTO.getState());
            client.setPostalCode(addressDTO.getZipCode());
        }
    }

    private AddressDTO mapAddressToDTO(Client client) {
        AddressDTO dto = new AddressDTO();
        dto.setStreet(client.getStreet());
        dto.setNumber(client.getNumber());
        dto.setComplement(client.getComplement());
        dto.setCity(client.getCity());
        dto.setState(client.getState());
        dto.setZipCode(client.getPostalCode());
        return dto;
    }

    private ClientResponseDTO toResponseDTO(Client client) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setPhoneNumber(client.getPhoneNumber());
        dto.setAddress(mapAddressToDTO(client));
        return dto;
    }

}
