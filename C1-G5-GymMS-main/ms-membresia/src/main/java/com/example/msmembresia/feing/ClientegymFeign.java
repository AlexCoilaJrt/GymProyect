package com.example.msmembresia.feing;

import com.example.msmembresia.dto.ClientegymDto;
import com.example.msmembresia.entity.Membresia;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-clientegym-service", path = "/clientegym")
public interface ClientegymFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clienteListarPorIdCB", fallbackMethod = "fallbackClientePorId")
    public ResponseEntity<ClientegymDto> buscarPorId(@PathVariable(required = true) Integer id);
    default ResponseEntity<ClientegymDto> fallbackClientePorId(Integer id, Exception e) {
        return ResponseEntity.ok(new ClientegymDto());
    }}
