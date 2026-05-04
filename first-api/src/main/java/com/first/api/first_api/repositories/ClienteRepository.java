package com.first.api.first_api.repositories;

import com.first.api.first_api.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // JpaRepository ya incluye save(), findById(), findAll(), etc.
    
    // Podemos agregar métodos personalizados mágicos de Spring:
    // Este método buscará automáticamente un cliente por su DNI
    Cliente findByDniCuit(String dniCuit);
}