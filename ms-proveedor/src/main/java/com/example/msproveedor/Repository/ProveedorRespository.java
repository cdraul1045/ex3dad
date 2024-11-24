package com.example.msproveedor.Repository;

import com.example.msproveedor.Entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRespository extends JpaRepository<Proveedor, Integer> {
    // Esta interfaz hereda de JpaRepository, lo que proporciona métodos CRUD para la entidad Proveedor
}
