package com.example.msenvio.feign;

import com.example.msenvio.dto.ClienteDto; // Importa el DTO Cliente
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker; // Importa la anotación para CircuitBreaker
import org.springframework.cloud.openfeign.FeignClient; // Importa la anotación para Feign Client
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity
import org.springframework.web.bind.annotation.GetMapping; // Importa la anotación para solicitudes GET
import org.springframework.web.bind.annotation.PathVariable; // Importa la anotación para variables de ruta

@FeignClient(name = "ms-Cliente-service", path = "/cliente") // Define el cliente Feign para comunicarse con el microservicio de cliente
public interface ClienteFeign {

    @GetMapping("/{id}") // Mapea la solicitud GET a "/cliente/{id}"
    @CircuitBreaker(name = "clienteService", fallbackMethod = "fallbackGetById") // Agrega el CircuitBreaker
    public ResponseEntity<ClienteDto> getById(@PathVariable("id") Integer id); // Método para obtener un cliente por su ID

    // Método de fallback
    default ResponseEntity<ClienteDto> fallbackGetById(Integer id, Exception e) {
        // Puedes retornar un objeto por defecto en caso de que falle el servicio
        return ResponseEntity.ok(new ClienteDto()); // Retorna un cliente vacío o con valores predeterminados
    }
}
