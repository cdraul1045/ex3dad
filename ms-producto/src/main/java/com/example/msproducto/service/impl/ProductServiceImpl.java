package com.example.msproducto.service.impl;

import com.example.msproducto.entity.Producto;
import com.example.msproducto.repository.ProductoRepository;
import com.example.msproducto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductoRepository productoRepository;

    // Listar todos los productos
    @Override
    public List<Producto> list() {
        return productoRepository.findAll();
    }

    // Guardar un producto sin imagen
    @Override
    public Producto save(Producto product) {
        return productoRepository.save(product);
    }

    // Guardar un producto con imagen
    @Override
    public Producto saveWithImage(Producto product, MultipartFile imagen) throws IOException {
        // Convertir la imagen a bytes y asignarla al producto
        byte[] imagenBytes = imagen.getBytes();
        product.setImagen(imagenBytes);
        return productoRepository.save(product);
    }

    // Buscar un producto por ID
    @Override
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }

    // Actualizar un producto existente
    @Override
    public Producto update(Producto product) {
        return productoRepository.save(product);  // El método save también funciona para actualizar
    }

    // Eliminar un producto por ID
    @Override
    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }
}
