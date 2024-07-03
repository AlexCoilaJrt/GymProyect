package com.example.msinscripcion.feing;

import com.example.msinscripcion.dto.ClaseinscripcionDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "ms-clase-service", path = "/clase")
public interface ClaseinscripcionFeing {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "claseinscripcionListarPorId", fallbackMethod = "fallbackClasePorId")
    public ResponseEntity<ClaseinscripcionDto> buscarPorId(@PathVariable Integer id);

    default ResponseEntity<ClaseinscripcionDto> fallbackClasePorId(Integer id, Exception e) {
        return ResponseEntity.ok(new ClaseinscripcionDto());
    }

    @PutMapping("/reducirCupo/{id}")
    void reducirCupo(@PathVariable("id") Integer id);
}
