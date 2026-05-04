package com.first.api.first_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Set;

@Entity
@Table(name = "companias")
@Data
public class Compania {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "companias_ramos", // Nombre de la tabla intermedia en la BD
        joinColumns = @JoinColumn(name = "compania_id"),
        inverseJoinColumns = @JoinColumn(name = "ramo_id")
    )
    private Set<Ramo> ramos;
}