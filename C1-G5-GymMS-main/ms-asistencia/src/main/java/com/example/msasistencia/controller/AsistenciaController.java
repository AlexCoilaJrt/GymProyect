package com.example.msasistencia.controller;

import com.example.msasistencia.entity.Asistencia;
import com.example.msasistencia.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {
    @Autowired
    private AsistenciaService asistenciaService;


    @GetMapping()
    public ResponseEntity<List<Asistencia>> listar() {
        return ResponseEntity.ok().body(asistenciaService.listar());
    }
    @PostMapping()
    public ResponseEntity<Asistencia> guardar(@RequestBody Asistencia asistencia){
        return ResponseEntity.ok(asistenciaService.guardar(asistencia));
    }
    @PutMapping()
    public ResponseEntity<Asistencia> buscarPorId(@RequestBody Asistencia asistencia){
        return ResponseEntity.ok(asistenciaService.buscarPorId(asistencia));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> editar(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok().body(asistenciaService.editar(id).get());
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id){
        asistenciaService.eliminar(id);
        return "Eliminacion Correcta";
    }
}

