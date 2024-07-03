package com.example.msasistencia.repository;

import com.example.msasistencia.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsistenciaRepository extends JpaRepository<Asistencia,Integer> {
}
