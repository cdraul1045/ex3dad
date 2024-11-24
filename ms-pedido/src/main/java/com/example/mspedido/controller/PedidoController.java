package com.example.mspedido.controller;

import com.example.mspedido.dto.ClienteDto;
import com.example.mspedido.dto.ErrorResponseDto;
import com.example.mspedido.dto.ProductoDto;
import com.example.mspedido.entity.Pedido;
import com.example.mspedido.entity.PedidoDetalle;
import com.example.mspedido.feign.ProductoFeign;
import com.example.mspedido.feign.ClienteFeign;
import com.example.mspedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private ClienteFeign clienteFeign;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        return ResponseEntity.ok(pedidoService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pedido pedido) {
        ClienteDto clienteDto = clienteFeign.getById(pedido.getClienteId()).getBody();

        if (clienteDto == null || clienteDto.getId() == null) {
            String errorMessage = "Error: Cliente no encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
        }
        for (PedidoDetalle pedidoDetalle : pedido.getPedidodetalle()) {
            ProductoDto productoDto = productoFeign.getById(pedidoDetalle.getProductoId()).getBody();

            if (productoDto == null || productoDto.getId() == null) {
                String errorMessage = "Error: Producto no encontrado.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
            }
        }
        Pedido nuevoPedido = pedidoService.save(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Integer id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        return ResponseEntity.ok(pedidoService.save(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Pedido>> delete(@PathVariable Integer id) {
        pedidoService.delete(id);
        return ResponseEntity.ok(pedidoService.list());
    }
}
