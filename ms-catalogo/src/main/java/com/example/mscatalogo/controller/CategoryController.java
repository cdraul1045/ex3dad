package com.example.mscatalogo.controller;

import com.example.mscatalogo.entity.Category;
import com.example.mscatalogo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        List<Category> categories = categoryService.list();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Integer id, @RequestBody Category category) {
        Optional<Category> existingCategory = categoryService.findById(id);

        if (existingCategory.isEmpty()) {
            return ResponseEntity.notFound().build();  // Retorna 404 si la categoría no existe.
        }

        category.setId(id);  // Asegúrate de que el ID de la categoría a actualizar sea el correcto.
        Category updatedCategory = categoryService.update(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> listById(@PathVariable Integer id) {
        Optional<Category> category = categoryService.findById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());  // Retorna 404 si la categoría no existe.
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        Optional<Category> category = categoryService.findById(id);

        if (category.isEmpty()) {
            return ResponseEntity.notFound().build();  // Retorna 404 si la categoría no existe.
        }

        categoryService.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content si la eliminación es exitosa
    }
}
