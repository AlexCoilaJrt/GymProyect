package com.example.mscatalogogym.service;

import com.example.mscatalogogym.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    public List<Categoria> listar();
    public Categoria guardar(Categoria categoria);
    public Categoria buscarPorId(Integer Id);
    public Categoria editar(Categoria categoria);
    public void eliminar(Integer Id);
}
