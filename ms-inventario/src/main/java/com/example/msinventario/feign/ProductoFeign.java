package com.example.msinventario.feign;

import com.example.msinventario.dto.ProductoDto; // Importa el DTO de Producto
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker; // Importa la anotación CircuitBreaker
import org.springframework.cloud.openfeign.FeignClient; // Importa la anotación FeignClient para crear clientes HTTP
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity para manejar respuestas HTTP
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación para manejar solicitudes GET
import org.springframework.web.bind.annotation.PathVariable; // Importa la anotación para manejar variables de ruta

@FeignClient(name = "ms-producto-service", path = "/product")
public interface ProductoFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "productListByIdCB", fallbackMethod = "productListById")
    public ResponseEntity<ProductoDto> getById(@PathVariable Integer id);
    default ResponseEntity<ProductoDto> productListById(Integer id, Exception e) {
        return ResponseEntity.ok(new ProductoDto());
    }

}