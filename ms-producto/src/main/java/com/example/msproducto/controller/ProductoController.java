package com.example.msproducto.controller;

import com.example.msproducto.entity.Producto;
import com.example.msproducto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductoController {

    @Autowired
    private ProductService productService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> list() {
        List<Producto> products = productService.list();
        return ResponseEntity.ok(products);
    }

    // Crear un nuevo producto con imagen
    @PostMapping
    public ResponseEntity<Producto> save(
            @RequestPart("producto") Producto producto,  // Datos del producto
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {  // Imagen del producto

        Producto savedProduct;
        try {
            if (imagen != null && !imagen.isEmpty()) {
                // Guardar el producto junto con la imagen
                savedProduct = productService.saveWithImage(producto, imagen);
            } else {
                // Si no se proporciona imagen, solo guardar el producto
                savedProduct = productService.save(producto);
            }
            return ResponseEntity.ok(savedProduct);  // Devolver el producto guardado
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();  // Manejar error de lectura de la imagen
        }
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(
            @PathVariable Integer id,
            @RequestPart(value = "producto") Producto producto, // Datos del producto
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {  // Imagen del producto

        // Buscar el producto por ID
        Optional<Producto> existingProduct = productService.findById(id);
        if (!existingProduct.isPresent()) {
            return ResponseEntity.notFound().build(); // Si no existe el producto, devolver 404
        }

        try {
            // Si se proporciona una nueva imagen, se convierte en bytes y se asigna al producto
            if (imagen != null && !imagen.isEmpty()) {
                byte[] imagenBytes = imagen.getBytes();  // Convertir la imagen a bytes
                producto.setImagen(imagenBytes);         // Establecer los bytes de la imagen en el producto
            }

            // Asegurarse de que el producto tiene el ID correcto
            producto.setId(id);
            Producto updatedProduct = productService.update(producto);  // Actualizar el producto
            return ResponseEntity.ok(updatedProduct);  // Devolver el producto actualizado
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();  // Manejar error de lectura de la imagen
        }
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> listById(@PathVariable Integer id) {
        Optional<Producto> product = productService.findById(id);  // Buscar el producto por ID
        return product.map(ResponseEntity::ok)  // Si el producto existe, devolverlo con estado 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build());  // Si no existe, devolver 404 Not Found
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        Optional<Producto> product = productService.findById(id);
        if (product.isPresent()) {
            productService.deleteById(id);  // Eliminar el producto por ID
            return ResponseEntity.noContent().build();  // Devolver estado 204 No Content en caso de éxito
        } else {
            return ResponseEntity.notFound().build();  // Si no se encuentra el producto, devolver 404 Not Found
        }
    }
}
