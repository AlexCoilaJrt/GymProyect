package com.example.mspagomembresia.feing;

import com.example.mspagomembresia.dto.ClientegymDto;
import com.example.mspagomembresia.dto.MembresiaDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "ms-membresia-service", path = "/membresia")

public interface MembresiaFeing {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "membresiaListarPorId", fallbackMethod = "fallbackMembresiaPorId")
    public ResponseEntity<MembresiaDto> buscarPorId(@PathVariable(required = true)Integer id);
    default ResponseEntity<MembresiaDto> fallbackMembresiaPorId(Integer id, Exception e) {
        return ResponseEntity.ok(new MembresiaDto());
    }
}
