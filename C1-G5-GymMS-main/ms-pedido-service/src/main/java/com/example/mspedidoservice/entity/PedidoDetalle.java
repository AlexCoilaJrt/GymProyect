package com.example.mspedidoservice.entity;

import com.example.mspedidoservice.dto.ProductoDto;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Entity
public class PedidoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double cantidad;
    private Double precio;
    private Double precioConIgv;
    private Integer productoId;


    public PedidoDetalle() {
        this.cantidad = (double) 0;
        this.precio = (double) 0;
        this.precioConIgv = 0.0;
    }

    @Transient
    ProductoDto producto;

    public void calcularPrecioConIgv() {
        BigDecimal precioBD = BigDecimal.valueOf(this.precio).setScale(2, RoundingMode.HALF_UP);
        BigDecimal igv = precioBD.multiply(new BigDecimal("0.18")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal precioConIgvBD = precioBD.add(igv).setScale(2, RoundingMode.HALF_UP);
        this.precioConIgv = precioConIgvBD.doubleValue();
    }
}
