package com.first.api.first_api.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dniCuit;
    private String telefono;
    private String email;
}