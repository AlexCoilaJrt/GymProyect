package com.example.msinscripcion.feing;

import com.example.msinscripcion.dto.ClienteinscripcionDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-clientegym-service", path = "/clientegym")

public interface ClienteinscripcionFeing {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clienteinscripcionListarPorId", fallbackMethod = "fallbackClientePorId")
    public ResponseEntity<ClienteinscripcionDto> buscarPorId(@PathVariable(required = true)Integer id);
    default ResponseEntity<ClienteinscripcionDto> fallbackClientePorId(Integer id, Exception e) {
        return ResponseEntity.ok(new ClienteinscripcionDto());
    }
}
