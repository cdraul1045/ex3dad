package com.example.msproveedor.Service.Impl;

import com.example.msproveedor.Entity.Proveedor; // Importa la entidad Proveedor
import com.example.msproveedor.Repository.ProveedorRespository; // Importa el repositorio de Proveedores
import com.example.msproveedor.Service.ProveedorService; // Importa la interfaz del servicio de Proveedor
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para inyección de dependencias
import org.springframework.stereotype.Service; // Importa la anotación para definir un servicio

import java.util.List; // Importa la clase List
import java.util.Optional; // Importa la clase Optional

@Service // Indica que esta clase es un servicio de Spring
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRespository proveedorRepository; // Inyección del repositorio de proveedores

    @Override
    public List<Proveedor> list() {
        return proveedorRepository.findAll(); // Obtiene todos los proveedores
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor); // Guarda un nuevo proveedor
    }

    @Override
    public Proveedor update(Proveedor proveedor) {
        return proveedorRepository.save(proveedor); // Actualiza un proveedor existente
    }

    @Override
    public Optional<Proveedor> findById(Integer id) {
        return proveedorRepository.findById(id); // Busca un proveedor por ID
    }

    @Override
    public Optional<Proveedor> listarPorId(Integer id) {
        return proveedorRepository.findById(id); // Reutiliza el método findById para obtener el proveedor
    }

    @Override
    public void deleteById(Integer id) {
        proveedorRepository.deleteById(id); // Elimina un proveedor por ID
    }
}
