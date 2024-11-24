package com.example.msenvio.feign;

import com.example.msenvio.dto.ProductoDto; // Importa el DTO Producto
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker; // Importa la anotación para CircuitBreaker
import org.springframework.cloud.openfeign.FeignClient; // Importa la anotación para Feign Client
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación para solicitudes GET
import org.springframework.web.bind.annotation.PathVariable; // Importa la anotación para variables de ruta

@FeignClient(name = "ms-Producto-service", path = "/product") // Define el cliente Feign para comunicarse con el microservicio de producto
public interface ProductoFeign {

    @GetMapping("/{id}") // Mapea la solicitud GET a "/producto/{id}"
    @CircuitBreaker(name = "productoService", fallbackMethod = "fallbackGetById") // Agrega el CircuitBreaker
    public ResponseEntity<ProductoDto> getById(@PathVariable("id") Integer id); // Método para obtener un producto por su ID

    // Método de fallback
    default ResponseEntity<ProductoDto> fallbackGetById(Integer id, Exception e) {
        // Puedes retornar un objeto por defecto en caso de que falle el servicio
        return ResponseEntity.ok(new ProductoDto()); // Retorna un producto vacío o con valores predeterminados
    }
}
