package com.example.mscliente.repository;

import com.example.mscliente.entity.Cliente; // Importa la entidad Cliente
import org.springframework.data.jpa.repository.JpaRepository; // Importa la interfaz JpaRepository

// Interfaz ClienteRepository que extiende JpaRepository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Esta interfaz hereda métodos para operaciones CRUD sobre la entidad Cliente
}
