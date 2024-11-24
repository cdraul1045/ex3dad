package org.example.msauth.entity;

import lombok.AllArgsConstructor; // Para generar un constructor que acepte todos los argumentos.
import lombok.Builder; // Para implementar el patrón Builder.
import lombok.Data; // Para generar métodos getter, setter, toString, equals y hashCode automáticamente.
import lombok.NoArgsConstructor; // Para generar un constructor sin argumentos.

import javax.persistence.Entity; // Anotación de JPA que indica que esta clase es una entidad.
import javax.persistence.GeneratedValue; // Para indicar que el valor del campo es generado automáticamente.
import javax.persistence.GenerationType; // Para especificar la estrategia de generación de valores.
import javax.persistence.Id; // Anotación de JPA que indica que este campo es la clave primaria.

@Entity // Marca la clase como una entidad JPA.
@Data // Genera automáticamente los métodos comunes como getter, setter, etc.
@Builder // Permite el uso del patrón Builder para crear instancias de la clase.
@NoArgsConstructor // Genera un constructor sin argumentos.
@AllArgsConstructor // Genera un constructor que acepta todos los argumentos.
public class AuthUser {
    @Id // Indica que este campo es la clave primaria de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que el valor es generado automáticamente por la base de datos.
    private Integer id; // Identificador único del usuario.

    private String userName; // Nombre de usuario.

    private String password; // Contraseña del usuario.
}
