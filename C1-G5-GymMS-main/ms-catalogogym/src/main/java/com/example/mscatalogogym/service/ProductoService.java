package com.example.mscatalogogym.service;

import com.example.mscatalogogym.entity.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> listar();
    public Producto guardar(Producto producto);
    public Producto buscarPorId(Integer Id);
    public Producto editar(Producto producto);
    public void eliminar(Integer Id);
}
