package com.example.msinscripcion.service;

import com.example.msinscripcion.entity.Inscripcion;

import java.util.List;

public interface InscripcionService {
    public List<Inscripcion> listar();
    public Inscripcion guardar(Inscripcion inscripcion);
    public Inscripcion buscarPorId(Integer Id);
    public Inscripcion editar(Inscripcion inscripcion);
    public void eliminar(Integer Id);

    public Inscripcion registrar(Inscripcion inscripcion);
}
