package com.example.mspagoclase.dto;

import lombok.Data;

@Data
public class ClaseDto {
    private Integer id;
    private String tipo; // Aeróbicos, Box, Step-Box, etc.
    private String hora;
    private Double costo;
    private Integer cuposDisponibles;


}
