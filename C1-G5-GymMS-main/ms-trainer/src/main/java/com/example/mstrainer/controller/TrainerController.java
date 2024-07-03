package com.example.mstrainer.controller;

import com.example.mstrainer.entity.Trainer;
import com.example.mstrainer.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @GetMapping
    public ResponseEntity<List<Trainer>> listar(){
        return ResponseEntity.ok(trainerService.listar());
    }

    @PostMapping
    public ResponseEntity<Trainer> guardar(@RequestBody Trainer trainer){

        return  ResponseEntity.ok(trainerService.guardar(trainer));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> buscarPorId(@PathVariable(required = true) Integer id){
        return  ResponseEntity.ok(trainerService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Trainer> editar(@PathVariable(required = true)Integer id,@RequestBody Trainer trainer){
        return ResponseEntity.ok(trainerService.editar(trainer));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id){
        trainerService.eliminar(id);
        return "Eliminacion Correcta";

    }
}
