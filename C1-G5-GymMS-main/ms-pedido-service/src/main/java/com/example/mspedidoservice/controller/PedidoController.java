package com.example.mspedidoservice.controller;


import com.example.mspedidoservice.entity.Pedido;
import com.example.mspedidoservice.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @GetMapping
    public ResponseEntity<List<Pedido>> listar(){
        return ResponseEntity.ok(pedidoService.listar());
    }

    @PostMapping
    public ResponseEntity<Pedido> guardar(@RequestBody Pedido pedido) {
        Pedido pedidoGuardado = pedidoService.guardar(pedido);
        return ResponseEntity.ok(pedidoGuardado);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable(required = true) Integer id){
        return  ResponseEntity.ok(pedidoService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> editar(@PathVariable(required = true)Integer id,@RequestBody Pedido pedido){
        return ResponseEntity.ok(pedidoService.editar(pedido));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id){
        pedidoService.eliminar(id);
        return "Eliminacion Correcta";

    }
}
