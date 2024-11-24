package com.example.mscatalogo.service;

import com.example.mscatalogo.entity.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> list();  // Devuelve una lista de categorías

    Category save(Category category);  // Guarda una nueva categoría

    Category update(Category category);  // Actualiza una categoría existente

    Optional<Category> findById(Integer id);  // Busca una categoría por su ID

    void deleteById(Integer id);  // Elimina una categoría por su ID
}
