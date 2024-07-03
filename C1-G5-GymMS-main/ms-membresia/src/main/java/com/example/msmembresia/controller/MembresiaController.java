package com.example.msmembresia.controller;

import com.example.msmembresia.entity.Membresia;
import com.example.msmembresia.service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membresia")

public class MembresiaController {
    @Autowired
    private MembresiaService membresiaService;


    @GetMapping
    public ResponseEntity<List<Membresia>> listar() {
        return ResponseEntity.ok(membresiaService.listar());
    }

    @PostMapping
    public ResponseEntity<Membresia> guardar(@RequestBody Membresia membresia) {

        return ResponseEntity.ok(membresiaService.guardar(membresia));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Membresia> buscarPorId(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok(membresiaService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Membresia> editar(@PathVariable(required = true) Integer id, @RequestBody Membresia membresia) {
        return ResponseEntity.ok(membresiaService.editar(membresia));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id) {
        membresiaService.eliminar(id);
        return "Eliminacion Correcta";

    }
}