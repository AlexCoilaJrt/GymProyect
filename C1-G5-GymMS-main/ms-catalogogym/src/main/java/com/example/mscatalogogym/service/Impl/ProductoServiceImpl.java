package com.example.mscatalogogym.service.Impl;

import com.example.mscatalogogym.entity.Producto;
import com.example.mscatalogogym.repository.ProductoRepository;
import com.example.mscatalogogym.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id).get();
    }

    @Override
    public Producto editar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }
}
