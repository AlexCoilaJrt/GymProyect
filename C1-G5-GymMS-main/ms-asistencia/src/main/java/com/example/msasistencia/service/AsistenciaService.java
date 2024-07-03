package com.example.msasistencia.service;

import com.example.msasistencia.entity.Asistencia;

import java.util.List;
import java.util.Optional;

public interface AsistenciaService {
    public List<Asistencia> listar();
    public Asistencia guardar(Asistencia asistencia);
    public Asistencia buscarPorId(Asistencia asistencia);
    public Optional<Asistencia> editar(Integer id);
    public void eliminar(Integer id);
}
