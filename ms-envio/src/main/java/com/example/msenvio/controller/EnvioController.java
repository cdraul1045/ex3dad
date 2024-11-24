package com.example.msenvio.controller;

import com.example.msenvio.dto.ClienteDto;
import com.example.msenvio.dto.ErrorResponseDto;
import com.example.msenvio.dto.ProductoDto;
import com.example.msenvio.entity.Envio;
import com.example.msenvio.entity.EnvioDetalle;
import com.example.msenvio.feign.ProductoFeign;
import com.example.msenvio.feign.ClienteFeign;
import com.example.msenvio.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/envio")  // Mapeo base para las rutas relacionadas con envío
public class EnvioController {

    @Autowired
    private EnvioService envioService;  // Servicio para la lógica de negocio relacionada con envíos

    @Autowired
    private ProductoFeign productoFeign; // Cliente Feign para interactuar con el servicio de productos

    @Autowired
    private ClienteFeign clienteFeign;   // Cliente Feign para interactuar con el servicio de clientes

    // Obtener todos los envíos
    @GetMapping
    public ResponseEntity<List<Envio>> getAll() {
        return ResponseEntity.ok(envioService.list()); // Devuelve la lista de envíos
    }

    // Obtener un envío por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Envio>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(envioService.findById(id)); // Devuelve el envío correspondiente al ID
    }

    // Crear un nuevo envío
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Envio envio) {
        // Verificar si el cliente existe
        ClienteDto clienteDto = clienteFeign.getById(envio.getClienteId()).getBody();
        if (clienteDto == null || clienteDto.getId() == null) {
            String errorMessage = "Error: Cliente no encontrado."; // Mensaje de error si el cliente no se encuentra
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
        }

        // Verificar si los productos existen
        for (EnvioDetalle envioDetalle : envio.getEnvioDetalle()) {
            ProductoDto productoDto = productoFeign.getById(envioDetalle.getProductoId()).getBody();
            if (productoDto == null || productoDto.getId() == null) {
                String errorMessage = "Error: Producto no encontrado."; // Mensaje de error si un producto no se encuentra
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
            }
        }

        // Guardar el nuevo envío
        Envio nuevoEnvio = envioService.save(envio);
        return ResponseEntity.ok(nuevoEnvio); // Devuelve el nuevo envío creado
    }

    // Actualizar un envío existente
    @PutMapping("/{id}")
    public ResponseEntity<Envio> update(@PathVariable Integer id, @RequestBody Envio envio) {
        envio.setId(id); // Establece el ID del envío a actualizar
        return ResponseEntity.ok(envioService.save(envio)); // Guarda el envío actualizado y lo devuelve
    }

    // Eliminar un envío por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Envio>> delete(@PathVariable Integer id) {
        envioService.delete(id); // Elimina el envío correspondiente al ID
        return ResponseEntity.ok(envioService.list()); // Devuelve la lista actualizada de envíos
    }
}
