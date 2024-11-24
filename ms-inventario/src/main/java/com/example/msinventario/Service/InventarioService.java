package com.example.msinventario.Service;

import com.example.msinventario.Entity.Inventario;

import java.util.List;
import java.util.Optional;

public interface InventarioService {
    public List<Inventario> list(); // Lista todos los inventarios.

    public Inventario save(Inventario inventario); // Guarda un nuevo inventario.

    public Inventario update(Inventario inventario); // Actualiza un inventario existente.

    Optional<Inventario> findById(Integer id); // Busca un inventario por ID y obtiene proveedor y detalles.

    public void delete(Integer id); // Elimina un inventario por ID.
}
