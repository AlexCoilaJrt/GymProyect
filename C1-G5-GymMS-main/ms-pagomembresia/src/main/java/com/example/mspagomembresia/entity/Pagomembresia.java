package com.example.mspagomembresia.entity;

import com.example.mspagomembresia.dto.ClientegymDto;
import com.example.mspagomembresia.dto.MembresiaDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data

public class Pagomembresia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double monto;
    private Date fechaPago;

    private Integer clientegymId;
    private Integer membresiaId;

    @Transient
    private ClientegymDto clientegymDto;

    @Transient
    private MembresiaDto membresiaDto;
}
