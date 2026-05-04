package com.first.api.first_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @Column(unique = true, name = "dni_cuit")
    @NotBlank(message = "El DNI/CUIT es obligatorio")
    private String dniCuit;

    private String telefono; // Nuevo campo

    @Email(message = "Formato de email inválido")
    private String email; // Nuevo campo con validación

    private boolean activo = true; 
}