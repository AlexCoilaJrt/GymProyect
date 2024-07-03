package com.example.mspagomembresia.service;

import com.example.mspagomembresia.entity.Pagomembresia;

import java.math.BigDecimal;
import java.util.List;

public interface PagoMembresiaService {
    public List<Pagomembresia> listar();
    public Pagomembresia guardar(Pagomembresia pagomembresia);
    public Pagomembresia buscarPorId(Integer Id);
    public Pagomembresia editar(Pagomembresia pagomembresia);
    public void eliminar(Integer Id);
    public void procesarPago(Double montoConIGV);
}
