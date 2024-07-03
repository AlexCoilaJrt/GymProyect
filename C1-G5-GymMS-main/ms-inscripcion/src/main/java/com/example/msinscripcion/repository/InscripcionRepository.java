package com.example.msinscripcion.repository;

import com.example.msinscripcion.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository <Inscripcion, Integer> {
}
