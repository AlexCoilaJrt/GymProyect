package com.example.mstrainer.service;

import com.example.mstrainer.entity.Trainer;

import java.util.List;

public interface TrainerService {
    public List<Trainer> listar();
    public Trainer guardar(Trainer trainer);
    public Trainer buscarPorId(Integer Id);
    public Trainer editar(Trainer trainer);
    public void eliminar(Integer Id);
}
