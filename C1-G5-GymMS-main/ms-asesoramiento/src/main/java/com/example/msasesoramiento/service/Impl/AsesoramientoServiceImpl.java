package com.example.msasesoramiento.service.Impl;

import com.example.msasesoramiento.entity.Asesoramiento;
import com.example.msasesoramiento.feing.ClienteaseFeing;
import com.example.msasesoramiento.feing.TrainerFeing;
import com.example.msasesoramiento.repository.AsesoramientoRepository;
import com.example.msasesoramiento.service.AsesoramientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsesoramientoServiceImpl implements AsesoramientoService {
    @Autowired
    private AsesoramientoRepository asesoramientoRepository;
    @Autowired
    private ClienteaseFeing clienteaseFeing;
    @Autowired
    private TrainerFeing trainerFeing;

    @Override
    public List<Asesoramiento> listar() {
        return asesoramientoRepository.findAll();
    }

    @Override
    public Asesoramiento guardar(Asesoramiento asesoramiento) {
        return asesoramientoRepository.save(asesoramiento);
    }

    @Override
    public Asesoramiento buscarPorId(Integer id) {
        Asesoramiento asesoramiento = asesoramientoRepository.findById(id).get();
        asesoramiento.setClienteaseDto(clienteaseFeing.buscarPorId(asesoramiento.getClientegymId()).getBody());
        asesoramiento.setTrainerDto(trainerFeing.buscarPorId(asesoramiento.getTrainerId()).getBody());
        return asesoramiento;
    }

    @Override
    public Asesoramiento editar(Asesoramiento asesoramiento) {
        return asesoramientoRepository.save(asesoramiento);
    }

    @Override
    public void eliminar(Integer id) {
        asesoramientoRepository.deleteById(id);
    }

}
