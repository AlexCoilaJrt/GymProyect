package com.example.msclientegym.controller;

import com.example.msclientegym.entity.ClienteGym;
import com.example.msclientegym.service.ClienteGymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientegym")

public class ClienteGymController {
    @Autowired
    private ClienteGymService clienteGymService; // Utiliza ClienteService en lugar de CategoriaService

    @GetMapping
    public ResponseEntity<List<ClienteGym>>listar(){
        return ResponseEntity.ok(clienteGymService.listar());
    }

    @PostMapping
    public ResponseEntity<ClienteGym> guardar(@RequestBody ClienteGym clienteGym){

        return  ResponseEntity.ok(clienteGymService.guardar(clienteGym));

    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteGym> buscarPorId(@PathVariable(required = true) Integer id){
        return  ResponseEntity.ok(clienteGymService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteGym> editar(@PathVariable(required = true)Integer id,@RequestBody ClienteGym clienteGym){
        return ResponseEntity.ok(clienteGymService.editar(clienteGym));
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable(required = true) Integer id){
        clienteGymService.eliminar(id);
        return "Eliminacion Correcta";

    }

}