package com.example.msasesoramiento.entity;

import com.example.msasesoramiento.dto.ClienteaseDto;
import com.example.msasesoramiento.dto.TrainerDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Asesoramiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String tipoMembresia;
    private String objetivo;
    private String planAlimentacion;
    private String rutina;
    private Date fechaInicio;
    private Date fechaFin;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Integer clientegymId;
    private Integer trainerId;

    @Transient
    private ClienteaseDto clienteaseDto;

    @Transient
    private TrainerDto trainerDto;
}
