package com.example.msinscripcion.entity;

import com.example.msinscripcion.dto.ClaseinscripcionDto;
import com.example.msinscripcion.dto.ClienteinscripcionDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date fechaInscripcion;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Integer clientegymId;
    private Integer claseId;

    @Transient
    private ClienteinscripcionDto clienteinscripcionDto;
    @Transient
    private ClaseinscripcionDto claseinscripcionDto;
}
