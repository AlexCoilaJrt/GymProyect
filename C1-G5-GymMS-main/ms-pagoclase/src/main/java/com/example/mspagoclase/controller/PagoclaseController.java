package com.example.mspagoclase.controller;

import com.example.mspagoclase.entity.Pagoclase;
import com.example.mspagoclase.service.PagoclaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
@RestController
@RequestMapping("/pagoclase")
public class PagoclaseController {
    @Autowired
    private PagoclaseService pagoclaseService;

    @GetMapping
    public ResponseEntity<List<Pagoclase>> listar(){
        return ResponseEntity.ok(pagoclaseService.listar());
    }

    @PostMapping
    public ResponseEntity<Pagoclase> guardar(@RequestBody Pagoclase pagoclase) {
        Pagoclase pagoclaseGuardada = pagoclaseService.guardar(pagoclase);
        return ResponseEntity.ok(pagoclaseGuardada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagoclase> buscarPorId(@PathVariable(required = true) Integer id){
        return  ResponseEntity.ok(pagoclaseService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagoclase> editar(@PathVariable(required = true)Integer id, @RequestBody Pagoclase pagoclase){
        return ResponseEntity.ok(pagoclaseService.editar(pagoclase));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id){
        pagoclaseService.eliminar(id);
        return "Eliminacion Correcta";

    }
}
