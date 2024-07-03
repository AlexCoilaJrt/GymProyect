package com.example.mscatalogogym.repository;

import com.example.mscatalogogym.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
