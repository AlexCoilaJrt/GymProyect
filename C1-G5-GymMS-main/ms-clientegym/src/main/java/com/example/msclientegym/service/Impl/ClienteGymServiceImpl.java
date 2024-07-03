package com.example.msclientegym.service.Impl;

import com.example.msclientegym.entity.ClienteGym;
import com.example.msclientegym.repository.ClienteGymRepository;
import com.example.msclientegym.service.ClienteGymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteGymServiceImpl implements ClienteGymService {
    @Autowired
    private ClienteGymRepository clienteGymRepository;

    @Override
    public List<ClienteGym> listar() {
        return clienteGymRepository.findAll();
    }

    @Override
    public ClienteGym guardar(ClienteGym clienteGym) {
        return clienteGymRepository.save(clienteGym);
    }

    @Override
    public ClienteGym buscarPorId(Integer id) {
        return clienteGymRepository.findById(id).get();
    }

    @Override
    public ClienteGym editar(ClienteGym clienteGym) {
        return clienteGymRepository.save(clienteGym);
    }

    @Override
    public void eliminar(Integer id) {
        clienteGymRepository.deleteById(id);
    }
}
