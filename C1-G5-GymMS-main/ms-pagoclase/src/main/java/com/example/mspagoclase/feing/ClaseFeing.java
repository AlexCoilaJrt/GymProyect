package com.example.mspagoclase.feing;

import com.example.mspagoclase.dto.ClaseDto;
import com.example.mspagoclase.dto.ClientepagoclaseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-clase-service", path = "/clase")

public interface ClaseFeing {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clasePorId", fallbackMethod = "fallbackClasePorId")
    public ResponseEntity<ClaseDto> buscarPorId(@PathVariable(required = true)Integer id);
    default ResponseEntity<ClaseDto> fallbackClasePorId(Integer id, Exception e) {
        return ResponseEntity.ok(new ClaseDto());
    }
}
