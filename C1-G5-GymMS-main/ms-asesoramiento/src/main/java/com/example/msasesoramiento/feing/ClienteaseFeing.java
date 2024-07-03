package com.example.msasesoramiento.feing;

import com.example.msasesoramiento.dto.ClienteaseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-clientegym-service", path = "/clientegym")

public interface ClienteaseFeing {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clientegymListarPorId", fallbackMethod = "fallbackClientePorId")
    public ResponseEntity<ClienteaseDto> buscarPorId(@PathVariable(required = true)Integer id);
    default ResponseEntity<ClienteaseDto> fallbackClientePorId(Integer id, Exception e) {
        return ResponseEntity.ok(new ClienteaseDto());
    }
}
