package com.example.msinscripcion.controller;

import com.example.msinscripcion.entity.Inscripcion;
import com.example.msinscripcion.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<Inscripcion>> listar(){
        return ResponseEntity.ok(inscripcionService.listar());
    }

    @PostMapping
    public ResponseEntity<Inscripcion> guardar(@RequestBody Inscripcion inscripcion){

        return  ResponseEntity.ok(inscripcionService.guardar(inscripcion));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> buscarPorId(@PathVariable(required = true) Integer id){
        return  ResponseEntity.ok(inscripcionService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> editar(@PathVariable(required = true)Integer id, @RequestBody Inscripcion inscripcion){
        return ResponseEntity.ok(inscripcionService.editar(inscripcion));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id){
        inscripcionService.eliminar(id);
        return "Eliminacion Correcta";

    }
}
