package com.example.msasistencia.controller;

import com.example.msasistencia.entity.Asistencia;
import com.example.msasistencia.entity.Usuario;
import com.example.msasistencia.service.AsistenciaService;
import com.example.msasistencia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping()
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok().body(usuarioService.listar());
    }
    @PostMapping()
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.guardar(usuario));
    }
    @PutMapping()
    public ResponseEntity<Usuario> buscarPorId(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.buscarPorId(usuario));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> editar(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok().body(usuarioService.editar(id).get());
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id){
        usuarioService.eliminar(id);
        return "Eliminacion Correcta";
    }
}