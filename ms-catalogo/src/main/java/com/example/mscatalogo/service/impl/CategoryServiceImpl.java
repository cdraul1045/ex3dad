package com.example.mscatalogo.service.impl;

import com.example.mscatalogo.entity.Category;
import com.example.mscatalogo.repository.CategoryRepository;
import com.example.mscatalogo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        // Verificación para asegurarse de que la categoría existe antes de actualizarla
        if (!categoryRepository.existsById(category.getId())) {
            throw new RuntimeException("Category not found");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        // Aquí también se podría verificar si existe antes de borrar
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }


}
