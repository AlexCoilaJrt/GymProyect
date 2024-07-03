package com.example.msinstructor.service;

import com.example.msinstructor.entity.Instructor;

import java.util.List;

public interface InstructorService {
    public List<Instructor> listar();
    public Instructor guardar(Instructor instructor);
    public Instructor buscarPorId(Integer Id);
    public Instructor editar(Instructor instructor);
    public void eliminar(Integer Id);
}
