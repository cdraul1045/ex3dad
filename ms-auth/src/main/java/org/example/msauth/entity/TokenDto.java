package org.example.msauth.entity;

import lombok.AllArgsConstructor; // Para generar un constructor que acepte todos los argumentos.
import lombok.Builder; // Para implementar el patrón Builder.
import lombok.Data; // Para generar métodos getter, setter, toString, equals y hashCode automáticamente.
import lombok.NoArgsConstructor; // Para generar un constructor sin argumentos.

@NoArgsConstructor // Genera un constructor sin parámetros.
@AllArgsConstructor // Genera un constructor que acepta todos los campos como parámetros.
@Data // Genera automáticamente los métodos comunes como getter, setter, toString, equals y hashCode.
@Builder // Permite el uso del patrón Builder para crear instancias de la clase de manera fluida.
public class TokenDto {
    private String token; // Campo que almacena el token de autenticación.
}
