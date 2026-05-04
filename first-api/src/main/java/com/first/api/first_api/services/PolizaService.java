package com.first.api.first_api.services;

import com.first.api.first_api.models.Poliza;
import com.first.api.first_api.dto.PolizaDTO;
import com.first.api.first_api.repositories.PolizaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PolizaService {

    @Autowired
    private PolizaRepository polizaRepository;

    // --- MAPEO MANUAL (Entidad a DTO) ---
    private PolizaDTO convertirADTO(Poliza poliza) {
        PolizaDTO dto = new PolizaDTO();
        dto.setId(poliza.getId());
        dto.setNumeroPoliza(poliza.getNumeroPoliza());
        dto.setFechaInicio(poliza.getFechaInicio());
        dto.setFechaFin(poliza.getFechaFin());
        
        // Extraemos solo los nombres (¡Acá está la magia del DTO!)
        if (poliza.getCliente() != null) {
            dto.setNombreCliente(poliza.getCliente().getNombre() + " " + poliza.getCliente().getApellido());
        }
        if (poliza.getCompania() != null) {
            dto.setNombreCompania(poliza.getCompania().getNombre());
        }
        if (poliza.getRamo() != null) {
            dto.setNombreRamo(poliza.getRamo().getNombre());
        }
        
        return dto;
    }

    // --- MÉTODOS DEL SERVICIO ---

    public PolizaDTO guardarPoliza(Poliza poliza) {
        Poliza guardada = polizaRepository.save(poliza);
        return convertirADTO(guardada);
    }

    public Optional<PolizaDTO> buscarPorId(Long id) {
        return polizaRepository.findById(id).map(this::convertirADTO);
    }

    public List<PolizaDTO> obtenerPolizasActivas() {
        return polizaRepository.findByActivaTrue().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<PolizaDTO> obtenerPolizasPorUsuario(Long usuarioId) {
        return polizaRepository.findByUsuarioId(usuarioId).stream()
                .filter(Poliza::isActiva) // Solo las activas
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public void anularPoliza(Long id) {
        Optional<Poliza> polizaOpt = polizaRepository.findById(id);
        if (polizaOpt.isPresent()) {
            Poliza poliza = polizaOpt.get();
            poliza.setActiva(false);
            polizaRepository.save(poliza);
        } else {
            throw new RuntimeException("Póliza no encontrada");
        }
    }
}