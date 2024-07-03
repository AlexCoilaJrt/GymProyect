package com.example.mspagoclase.service;

import com.example.mspagoclase.entity.Pagoclase;

import java.util.List;

public interface PagoclaseService {
    public List<Pagoclase> listar();
    public Pagoclase guardar(Pagoclase pagoclase);
    public Pagoclase buscarPorId(Integer Id);
    public Pagoclase editar(Pagoclase pagoclase);
    public void eliminar(Integer Id);
    public void procesarPago(Double montoConIGV);

}
