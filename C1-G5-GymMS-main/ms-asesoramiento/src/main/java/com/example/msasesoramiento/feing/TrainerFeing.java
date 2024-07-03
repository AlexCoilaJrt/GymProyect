package com.example.msasesoramiento.feing;

import com.example.msasesoramiento.dto.TrainerDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-trainer-service", path = "/trainer")

public interface TrainerFeing {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "trainerListarPorId", fallbackMethod = "fallbackTrainerPorId")
    public ResponseEntity<TrainerDto> buscarPorId(@PathVariable(required = true)Integer id);
    default ResponseEntity<TrainerDto> fallbackTrainerPorId(Integer id, Exception e) {
        return ResponseEntity.ok(new TrainerDto());
    }
}
