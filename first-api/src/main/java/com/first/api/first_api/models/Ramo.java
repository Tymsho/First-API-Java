package com.first.api.first_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Set;

@Entity
@Table(name = "ramos")
@Data
public class Ramo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del ramo es obligatorio")
    @Column(unique = true)
    private String nombre; // Ej: Automotor, Vida, Hogar

    @ManyToMany(mappedBy = "ramos")
    private Set<Compania> companias;
}