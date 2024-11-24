package com.example.mscliente.entity;

import jakarta.persistence.Entity; // Importa la anotación para definir una entidad JPA
import jakarta.persistence.GeneratedValue; // Importa la anotación para generación automática de ID
import jakarta.persistence.GenerationType; // Importa los tipos de estrategia de generación
import jakarta.persistence.Id; // Importa la anotación para definir el ID de la entidad
import lombok.Data; // Importa la anotación de Lombok para generación automática de métodos

@Data // Genera automáticamente los métodos getter, setter, toString, equals y hashCode
@Entity // Indica que esta clase es una entidad JPA
public class Cliente {
    @Id // Define este campo como el identificador de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera el ID automáticamente
    private Integer id; // Identificador único del cliente

    private String nombre; // Nombre del cliente
    private String apellido; // Apellido del cliente
    private String email; // Correo electrónico del cliente
    private String telefono; // Número de teléfono del cliente
    private String direccion; // Dirección del cliente
}
