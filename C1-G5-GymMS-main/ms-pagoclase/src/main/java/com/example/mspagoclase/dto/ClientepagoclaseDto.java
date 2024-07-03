package com.example.mspagoclase.dto;

import lombok.Data;

@Data
public class ClientepagoclaseDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String genero;
    private String edad;
    private String telefono;
    private String correo;
    private String tipocliente;

    private InstructorDto instructorDto;

}
