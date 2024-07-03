package com.example.mstrainer.service.Impl;

import com.example.mstrainer.entity.Trainer;
import com.example.mstrainer.repository.TrainerRepository;
import com.example.mstrainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public List<Trainer> listar() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer guardar(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainer buscarPorId(Integer id) {
        return trainerRepository.findById(id).get();
    }

    @Override
    public Trainer editar(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public void eliminar(Integer id) {
        trainerRepository.deleteById(id);
    }

}
