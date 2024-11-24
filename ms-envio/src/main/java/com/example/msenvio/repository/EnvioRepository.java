package com.example.msenvio.repository;

import com.example.msenvio.entity.Envio; // Importa la entidad Envio
import org.springframework.data.jpa.repository.JpaRepository; // Importa JpaRepository para operaciones de base de datos

// Interfaz que extiende JpaRepository para realizar operaciones CRUD sobre la entidad Envio
public interface EnvioRepository extends JpaRepository<Envio, Integer> {

}
