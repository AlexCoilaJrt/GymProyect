package com.example.msasesoramiento.dto;

import lombok.Data;

@Data
public class ClienteaseDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String genero;
    private String edad;
    private String telefono;
    private String correo;
    private String tipocliente;
}
