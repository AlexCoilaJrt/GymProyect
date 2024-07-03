package com.example.msinstructor.controller;

import com.example.msinstructor.entity.Instructor;
import com.example.msinstructor.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<Instructor>> listar(){
        return ResponseEntity.ok(instructorService.listar());
    }

    @PostMapping
    public ResponseEntity<Instructor> guardar(@RequestBody Instructor instructor){

        return  ResponseEntity.ok(instructorService.guardar(instructor));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> buscarPorId(@PathVariable(required = true) Integer id){
        return  ResponseEntity.ok(instructorService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> editar(@PathVariable(required = true)Integer id, @RequestBody Instructor instructor){
        return ResponseEntity.ok(instructorService.editar(instructor));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id){
        instructorService.eliminar(id);
        return "Eliminacion Correcta";

    }
}
