package com.example.mspagoclase.feing;

import com.example.mspagoclase.dto.ClientepagoclaseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-clientegym-service", path = "/clientegym")

public interface ClientepagoclaseFeing {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clientepagoclaseListarPorId", fallbackMethod = "fallbackClientePorId")
    public ResponseEntity<ClientepagoclaseDto> buscarPorId(@PathVariable(required = true)Integer id);
    default ResponseEntity<ClientepagoclaseDto> fallbackClientePorId(Integer id, Exception e) {
        return ResponseEntity.ok(new ClientepagoclaseDto());
    }
}
