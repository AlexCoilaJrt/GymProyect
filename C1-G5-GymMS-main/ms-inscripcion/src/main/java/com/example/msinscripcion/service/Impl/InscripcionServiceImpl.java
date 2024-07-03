package com.example.msinscripcion.service.Impl;

import com.example.msinscripcion.entity.Inscripcion;
import com.example.msinscripcion.feing.ClaseinscripcionFeing;
import com.example.msinscripcion.feing.ClienteinscripcionFeing;
import com.example.msinscripcion.repository.InscripcionRepository;
import com.example.msinscripcion.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionServiceImpl implements InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private ClienteinscripcionFeing clienteinscripcionFeing;
    @Autowired
    private ClaseinscripcionFeing claseinscripcionFeing;

    @Override
    public Inscripcion registrar(Inscripcion inscripcion) {
        // Lógica para guardar la inscripción
        Inscripcion nuevaInscripcion = inscripcionRepository.save(inscripcion);

        // Llamada al Feign Client para reducir el cupo de la clase
        claseinscripcionFeing.reducirCupo(inscripcion.getClaseId());

        return nuevaInscripcion;
    }

    @Override
    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

    @Override
    public Inscripcion guardar(Inscripcion inscripcion) {
        // Guardar la inscripción
        Inscripcion nuevaInscripcion = inscripcionRepository.save(inscripcion);

        // Llamada al Feign Client para reducir el cupo de la clase
        claseinscripcionFeing.reducirCupo(inscripcion.getClaseId());

        return nuevaInscripcion;
    }
    @Override
    public Inscripcion buscarPorId(Integer id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id).get();
        inscripcion.setClienteinscripcionDto(clienteinscripcionFeing.buscarPorId(inscripcion.getClientegymId()).getBody());
        inscripcion.setClaseinscripcionDto(claseinscripcionFeing.buscarPorId(inscripcion.getClaseId()).getBody());
        return inscripcion;
    }
    @Override
    public Inscripcion editar(Inscripcion inscripcion) {return inscripcionRepository.save(inscripcion);}

    @Override
    public void eliminar(Integer id) {inscripcionRepository.deleteById(id);}
}
