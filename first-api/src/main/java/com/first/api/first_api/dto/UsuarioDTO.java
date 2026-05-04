package com.first.api.first_api.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    // ¡Ojo! No incluimos el password aquí.
}