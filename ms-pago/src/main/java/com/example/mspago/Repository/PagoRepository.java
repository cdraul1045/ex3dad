package com.example.mspago.Repository;

import com.example.mspago.Entity.Pago; // Importa la entidad Pago
import org.springframework.data.jpa.repository.JpaRepository; // Importa JpaRepository para la gestión de datos

// Define un repositorio para la entidad Pago, extendiendo JpaRepository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

}
