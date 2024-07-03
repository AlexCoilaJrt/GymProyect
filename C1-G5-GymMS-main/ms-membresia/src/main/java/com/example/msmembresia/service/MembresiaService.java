package com.example.msmembresia.service;

import com.example.msmembresia.entity.Membresia;

import java.util.List;
import java.util.Optional;

public interface MembresiaService {
    public List<Membresia> listar();
    public Membresia guardar(Membresia membresia);
    public Membresia buscarPorId(Integer id);
    public Membresia editar(Membresia membresia);
    public void eliminar(Integer id);
}
