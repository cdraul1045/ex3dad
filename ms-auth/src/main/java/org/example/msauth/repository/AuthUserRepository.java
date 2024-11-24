package org.example.msauth.repository;

import org.example.msauth.entity.AuthUser; // Importa la entidad AuthUser.
import org.springframework.data.jpa.repository.JpaRepository; // Importa la interfaz JpaRepository para operaciones CRUD.
import org.springframework.stereotype.Repository; // Anotación para marcar esta interfaz como un repositorio.

import java.util.Optional; // Importa Optional para manejar resultados que pueden ser nulos.

@Repository // Indica que esta interfaz es un repositorio de Spring.
public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    // Método para buscar un usuario por su nombre de usuario.
    Optional<AuthUser> findByUserName(String username);
}
