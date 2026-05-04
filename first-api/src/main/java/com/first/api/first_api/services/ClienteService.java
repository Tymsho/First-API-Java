package com.first.api.first_api.services;

import com.first.api.first_api.models.Cliente;
import com.first.api.first_api.dto.ClienteDTO;
import com.first.api.first_api.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // --- MAPEO MANUAL (Entidad a DTO) ---
    private ClienteDTO convertirADTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setDniCuit(cliente.getDniCuit());
        dto.setTelefono(cliente.getTelefono());
        dto.setEmail(cliente.getEmail());
        return dto;
    }

    // --- MÉTODOS DEL SERVICIO (Devuelven DTOs) ---

    // Obtener solo clientes activos
    public List<ClienteDTO> obtenerTodosActivos() {
        return clienteRepository.findAll().stream()
                .filter(Cliente::isActivo) // Filtramos los que tienen activo=true
                .map(this::convertirADTO)  // Convertimos cada Cliente a ClienteDTO
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public Optional<ClienteDTO> buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .filter(Cliente::isActivo)
                .map(this::convertirADTO);
    }

    // Guardar (Recibe una Entidad por ahora, pero devuelve el DTO guardado)
    public ClienteDTO guardarCliente(Cliente cliente) {
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return convertirADTO(clienteGuardado);
    }

    // Baja Lógica (No devuelve nada, pero anula el registro)
    public void bajaLogica(Long id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setActivo(false);
            clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
    }
}