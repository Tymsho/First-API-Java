package com.first.api.first_api.services;

import com.first.api.first_api.models.Cliente;
import com.first.api.first_api.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Le indica a Spring que esta clase es un Servicio
public class ClienteService {

    @Autowired // Inyecta el repositorio automáticamente
    private ClienteRepository clienteRepository;

    // Obtener todos los clientes (podrías filtrar solo los activos si quisieras)
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    // Buscar un cliente por ID
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Crear o actualizar un cliente
    public Cliente guardarCliente(Cliente cliente) {
        // Acá podrías agregar lógica, ej: verificar si el DNI ya existe
        return clienteRepository.save(cliente);
    }

    // Implementación de la BAJA LÓGICA (No usamos el delete() del repositorio)
    public void bajaLogica(Long id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setActivo(false); // Cambiamos el estado en lugar de borrar
            clienteRepository.save(cliente);
        } else {
            // Más adelante acá lanzaremos una excepción personalizada como pide tu TP
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
    }
}