package com.example.msclase.controller;

import com.example.msclase.entity.Clase;
import com.example.msclase.service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/clase")
public class ClaseController {
    @Autowired
    private ClaseService claseService;
    @PutMapping("/reducirCupo/{id}")
    public ResponseEntity<Clase> reducirCupo(@PathVariable Integer id) {
        Clase clase = claseService.reducirCupo(id);
        return ResponseEntity.ok(clase);
    }
    @GetMapping
    public ResponseEntity<List<Clase>> listar(){
        return ResponseEntity.ok(claseService.listar());
    }

    @PostMapping
    public ResponseEntity<Clase> guardar(@RequestBody Clase clase){

        return  ResponseEntity.ok(claseService.guardar(clase));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Clase> buscarPorId(@PathVariable(required = true) Integer id){
        return  ResponseEntity.ok(claseService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Clase> editar(@PathVariable(required = true)Integer id, @RequestBody Clase clase){
        return ResponseEntity.ok(claseService.editar(clase));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id){
        claseService.eliminar(id);
        return "Eliminacion Correcta";

    }
}
