package com.first.api.first_api.services;

import com.first.api.first_api.models.Poliza;
import com.first.api.first_api.repositories.PolizaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolizaService {

    @Autowired
    private PolizaRepository polizaRepository;

    public Poliza guardarPoliza(Poliza poliza) {
        return polizaRepository.save(poliza);
    }

    public Optional<Poliza> buscarPorId(Long id) {
        return polizaRepository.findById(id);
    }

    // Método para el Dashboard: Solo trae las pólizas activas
    public List<Poliza> obtenerPolizasActivas() {
        return polizaRepository.findByActivaTrue();
    }

    // Método para que un Productor vea solo su cartera
    public List<Poliza> obtenerPolizasPorUsuario(Long usuarioId) {
        return polizaRepository.findByUsuarioId(usuarioId);
    }

    // Baja lógica de la póliza
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