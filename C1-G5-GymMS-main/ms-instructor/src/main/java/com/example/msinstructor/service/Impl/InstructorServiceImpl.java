package com.example.msinstructor.service.Impl;

import com.example.msinstructor.entity.Instructor;
import com.example.msinstructor.repository.InstructorRepository;
import com.example.msinstructor.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    @Override
    public List<Instructor> listar() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor guardar(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor buscarPorId(Integer id) {
        return instructorRepository.findById(id).get();
    }

    @Override
    public Instructor editar(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public void eliminar(Integer id) {
        instructorRepository.deleteById(id);
    }
}

