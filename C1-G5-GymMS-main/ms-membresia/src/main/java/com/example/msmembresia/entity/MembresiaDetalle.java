package com.example.msmembresia.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class MembresiaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double descuento;

    public MembresiaDetalle() {
            // Example default value
        this.descuento = (double) 0;                       // Example default value
    }
}
