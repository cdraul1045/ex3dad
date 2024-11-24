package com.example.mspago.Controller;

import com.example.mspago.dto.ErrorResponseDto;
import com.example.mspago.dto.ProductoDto;
import com.example.mspago.dto.ClienteDto;
import com.example.mspago.Entity.Pago;
import com.example.mspago.Entity.PagoDetalle;
import com.example.mspago.feign.ProductoFeign;
import com.example.mspago.feign.ClienteFeign;
import com.example.mspago.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private ClienteFeign clienteFeign;

    @GetMapping
    public ResponseEntity<List<Pago>> getAll() {
        return ResponseEntity.ok(pagoService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pago>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(pagoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pago pago) {
        ClienteDto clienteDto = clienteFeign.getById(pago.getClienteId()).getBody();

        if (clienteDto == null || clienteDto.getId() == null) {
            String errorMessage = "Error: Cliente no encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
        }

        for (PagoDetalle pagoDetalle : pago.getPagoDetalle()) {
            ProductoDto productoDto = productoFeign.getById(pagoDetalle.getProductoId()).getBody();

            if (productoDto == null || productoDto.getId() == null) {
                String errorMessage = "Error: Producto no encontrado.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
            }
        }

        Pago nuevoPago = pagoService.save(pago);
        return ResponseEntity.ok(nuevoPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> update(@PathVariable Integer id, @RequestBody Pago pago) {
        pago.setId(id);
        return ResponseEntity.ok(pagoService.save(pago));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Pago>> delete(@PathVariable Integer id) {
        pagoService.delete(id);
        return ResponseEntity.ok(pagoService.list());
    }
}
