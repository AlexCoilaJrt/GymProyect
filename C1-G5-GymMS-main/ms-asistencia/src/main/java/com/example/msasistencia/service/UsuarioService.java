package com.example.msasistencia.service;

import com.example.msasistencia.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public List<Usuario> listar();
    public Usuario guardar(Usuario usuario);
    public Usuario buscarPorId(Usuario usuario);
    public Optional<Usuario> editar(Integer id);
    public void eliminar(Integer id);
}
