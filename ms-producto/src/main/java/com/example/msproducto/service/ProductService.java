package com.example.msproducto.service;

import com.example.msproducto.entity.Producto;
import org.springframework.web.multipart.MultipartFile; // Para manejar archivos
import java.io.IOException;                         // Para manejar errores de archivo
import java.util.List;
import java.util.Optional;

// Interfaz del servicio para manejar las operaciones CRUD relacionadas con los productos
public interface ProductService {

    // Método para listar todos los productos
    List<Producto> list();

    // Método para actualizar un producto existente
    Producto update(Producto product);

    // Método para buscar un producto por su ID
    Optional<Producto> findById(Integer id);

    // Método para eliminar un producto por su ID
    void deleteById(Integer id);
    Producto save(Producto product);

    // Este método guarda el producto con imagen
    Producto saveWithImage(Producto product, MultipartFile imagen) throws IOException;
}
