package com.example.msasistencia.service.impl;

import com.example.msasistencia.entity.Usuario;
import com.example.msasistencia.repository.UsuarioRepository;
import com.example.msasistencia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorId(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> editar(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
