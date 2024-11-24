package com.example.msinventario.Repository;

import com.example.msinventario.Entity.Inventario; // Importa la entidad Inventario
import org.springframework.data.jpa.repository.JpaRepository; // Importa JpaRepository para la gestión de datos

// Define un repositorio para la entidad Inventario, extendiendo JpaRepository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

}
