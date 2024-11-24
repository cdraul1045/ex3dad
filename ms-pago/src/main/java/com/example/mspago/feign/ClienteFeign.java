package com.example.mspago.feign;

import com.example.mspago.dto.ClienteDto; // Importa el DTO de Cliente
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-cliente-service", path = "/cliente")
public interface ClienteFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "clientByIdCB", fallbackMethod = "clientByIdFallback")
    public ResponseEntity<ClienteDto> getById(@PathVariable Integer id);

    // Método fallback para manejar fallos
    default ResponseEntity<ClienteDto> clientByIdFallback(Integer id, Exception e) {
        return ResponseEntity.ok(new ClienteDto());
    }
}