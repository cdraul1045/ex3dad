package com.example.mspedido.feign;

import com.example.mspedido.dto.ProductoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-producto-service", path = "/product")
public interface ProductoFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "productListByIdCB", fallbackMethod = "productListById")
    public ResponseEntity<ProductoDto> getById(@PathVariable Integer id);
    default ResponseEntity<ProductoDto> productListById(Integer id, Exception e) {
        return ResponseEntity.ok(new ProductoDto());
    }

}