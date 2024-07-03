package com.example.mspagoclase.entity;

import com.example.mspagoclase.dto.ClaseDto;
import com.example.mspagoclase.dto.ClientepagoclaseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Pagoclase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date fechaPago;
    private Double monto;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Integer clientegymId;
    private Integer claseId;

    @Transient
    private ClientepagoclaseDto clientepagoclaseDto;

    @Transient
    private ClaseDto claseDto;

}
