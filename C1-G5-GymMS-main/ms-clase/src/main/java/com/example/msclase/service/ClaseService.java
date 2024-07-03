package com.example.msclase.service;

import com.example.msclase.entity.Clase;

import java.util.List;

public interface ClaseService {
    public List<Clase> listar();
    public Clase guardar(Clase clase);
    public Clase buscarPorId(Integer Id);
    public Clase editar(Clase clase);
    public void eliminar(Integer Id);

    public Clase reducirCupo(Integer claseId);  // Método para reducir cupos
}
