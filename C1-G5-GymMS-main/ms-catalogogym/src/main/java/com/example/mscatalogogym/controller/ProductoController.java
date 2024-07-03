package com.example.mscatalogogym.controller;

import com.example.mscatalogogym.entity.Producto;
import com.example.mscatalogogym.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @GetMapping
    public ResponseEntity<List<Producto>>listar(){
        return ResponseEntity.ok(productoService.listar());
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto){

        return  ResponseEntity.ok(productoService.guardar(producto));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable(required = true) Integer id){
        return  ResponseEntity.ok(productoService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> editar(@PathVariable(required = true)Integer id,@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.editar(producto));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id){
        productoService.eliminar(id);
        return "Eliminacion Correcta";

    }
}
