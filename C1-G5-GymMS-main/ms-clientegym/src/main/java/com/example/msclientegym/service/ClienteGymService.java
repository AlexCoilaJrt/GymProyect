package com.example.msclientegym.service;

import com.example.msclientegym.entity.ClienteGym;
import com.example.msclientegym.repository.ClienteGymRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteGymService {
    public List<ClienteGym> listar();
    public ClienteGym guardar(ClienteGym clienteGym);
    public ClienteGym buscarPorId(Integer id);
    public ClienteGym editar(ClienteGym clienteGym);
    public void eliminar(Integer id);
}
