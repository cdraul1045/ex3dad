package com.example.msinventario.Controller;
import com.example.msinventario.dto.ErrorResponseDto;
import com.example.msinventario.dto.ProductoDto;
import com.example.msinventario.dto.ProveedorDto;
import com.example.msinventario.Entity.Inventario;
import com.example.msinventario.Entity.InventarioDetalle;
import com.example.msinventario.feign.ProductoFeign;
import com.example.msinventario.feign.ProveedorFeign;
import com.example.msinventario.Service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private ProveedorFeign proveedorFeign;

    @GetMapping
    public ResponseEntity<List<Inventario>> getAll() {
        return ResponseEntity.ok(inventarioService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Inventario>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(inventarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Inventario inventario) {
        ProveedorDto proveedorDto = proveedorFeign.getById(inventario.getProveedorId()).getBody();

        if (proveedorDto == null || proveedorDto.getId() == null) {
            String errorMessage = "Error: Proveedor no encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
        }

        for (InventarioDetalle inventarioDetalle : inventario.getInventarioDetalle()) {
            ProductoDto productoDto = productoFeign.getById(inventarioDetalle.getProductoId()).getBody();

            if (productoDto == null || productoDto.getId() == null) {
                String errorMessage = "Error: Producto no encontrado.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
            }
        }

        Inventario nuevoInventario = inventarioService.save(inventario);
        return ResponseEntity.ok(nuevoInventario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> update(@PathVariable Integer id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        return ResponseEntity.ok(inventarioService.save(inventario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Inventario>> delete(@PathVariable Integer id) {
        inventarioService.delete(id);
        return ResponseEntity.ok(inventarioService.list());
    }
}
