package com.example.mscatalogogym.repository;

import com.example.mscatalogogym.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
