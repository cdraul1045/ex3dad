package com.example.msinventario.feign;

import com.example.msinventario.dto.ProveedorDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-Proveedor-service", path = "/proveedor")
public interface ProveedorFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "proveedorGetByIdCB", fallbackMethod = "fallbackGetById")
    public ResponseEntity<ProveedorDto> getById(@PathVariable Integer id);

    // Fallback method when the service is down
    default ResponseEntity<ProveedorDto> fallbackGetById(Integer id, Throwable e) {
        // Log the exception or return a default value
        return ResponseEntity.ok(new ProveedorDto());
    }
}
