package org.example.msauth.dto;

import lombok.AllArgsConstructor; // Para generar un constructor que acepte todos los argumentos.
import lombok.Builder; // Para implementar el patrón Builder.
import lombok.Data; // Para generar métodos getter, setter, toString, equals y hashCode automáticamente.
import lombok.NoArgsConstructor; // Para generar un constructor sin argumentos.

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDto {
    // Campo que representa el nombre de usuario.
    private String userName;

    // Campo que representa la contraseña del usuario.
    private String password;
}
