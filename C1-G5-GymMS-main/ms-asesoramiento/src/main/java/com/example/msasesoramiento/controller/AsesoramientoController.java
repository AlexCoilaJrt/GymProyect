package com.example.msasesoramiento.controller;

import com.example.msasesoramiento.entity.Asesoramiento;
import com.example.msasesoramiento.service.AsesoramientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asesoramiento")
public class AsesoramientoController {
    @Autowired
    private AsesoramientoService asesoramientoService;

    @GetMapping
    public ResponseEntity<List<Asesoramiento>> listar(){
        return ResponseEntity.ok(asesoramientoService.listar());
    }

    @PostMapping
    public ResponseEntity<Asesoramiento> guardar(@RequestBody Asesoramiento asesoramiento){

        return  ResponseEntity.ok(asesoramientoService.guardar(asesoramiento));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Asesoramiento> buscarPorId(@PathVariable(required = true) Integer id){
        return  ResponseEntity.ok(asesoramientoService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Asesoramiento> editar(@PathVariable(required = true)Integer id, @RequestBody Asesoramiento asesoramiento){
        return ResponseEntity.ok(asesoramientoService.editar(asesoramiento));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id){
        asesoramientoService.eliminar(id);
        return "Eliminacion Correcta";

    }
}
