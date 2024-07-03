package com.example.msasesoramiento.service;

import com.example.msasesoramiento.entity.Asesoramiento;

import java.util.List;

public interface AsesoramientoService {
    public List<Asesoramiento> listar();
    public Asesoramiento guardar(Asesoramiento asesoramiento);
    public Asesoramiento buscarPorId(Integer Id);
    public Asesoramiento editar(Asesoramiento asesoramiento);
    public void eliminar(Integer Id);
}
