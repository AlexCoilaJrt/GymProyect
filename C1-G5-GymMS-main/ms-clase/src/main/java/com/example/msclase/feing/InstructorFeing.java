package com.example.msclase.feing;

import com.example.msclase.dto.InstructorDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-instructor-service", path = "/instructor")

public interface InstructorFeing {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clienteclassListarPorId", fallbackMethod = "fallbackClientePorId")
    public ResponseEntity<InstructorDto> buscarPorId(@PathVariable(required = true)Integer id);
    default ResponseEntity<InstructorDto> fallbackClientePorId(Integer id, Exception e) {
        return ResponseEntity.ok(new InstructorDto());
    }
}
