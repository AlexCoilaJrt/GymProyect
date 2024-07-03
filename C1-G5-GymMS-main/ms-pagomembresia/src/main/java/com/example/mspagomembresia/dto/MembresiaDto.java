package com.example.mspagomembresia.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MembresiaDto {
    private Integer id;
    private Date fechainicio;
    private Date fechafin;
    private String tipomembresia;
    private Double costo;


}
