package com.example.mspedidoservice.repository;

import com.example.mspedidoservice.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
