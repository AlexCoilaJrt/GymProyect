package com.example.msclase.entity;

import com.example.msclase.dto.ClienteclassDto;
import com.example.msclase.dto.InstructorDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo; // Aeróbicos, Box, Step-Box, etc.
    private String hora;
    private Double costo;
    private Integer cuposDisponibles;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Integer instructorId;

    @Transient
    private InstructorDto instructorDto;
}
