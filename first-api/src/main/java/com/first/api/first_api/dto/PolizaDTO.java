package com.first.api.first_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PolizaDTO {
    private Long id;
    private String numeroPoliza;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    // Datos aplanados para evitar bucles infinitos
    private String nombreCliente;
    private String nombreCompania;
    private String nombreRamo;
}