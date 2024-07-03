package com.example.mscatalogogym.controller;

import com.example.mscatalogogym.entity.Categoria;
import com.example.mscatalogogym.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;
    @GetMapping
    public ResponseEntity<List<Categoria>>listar(){
        return ResponseEntity.ok(categoriaService.listar());
    }

    @PostMapping
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria){

        return  ResponseEntity.ok(categoriaService.guardar(categoria));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable(required = true) Integer id){
        return  ResponseEntity.ok(categoriaService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editar(@PathVariable(required = true)Integer id,@RequestBody Categoria categoria){
        return ResponseEntity.ok(categoriaService.editar(categoria));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id){
        categoriaService.eliminar(id);
        return "Eliminacion Correcta";

    }
}
