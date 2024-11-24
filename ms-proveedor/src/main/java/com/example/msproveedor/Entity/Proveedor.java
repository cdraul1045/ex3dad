package com.example.msproveedor.Entity;

import jakarta.persistence.Entity; // Importa la anotación para definir una entidad JPA
import jakarta.persistence.GeneratedValue; // Importa la anotación para definir la generación de valores
import jakarta.persistence.GenerationType; // Importa los tipos de generación de valores
import jakarta.persistence.Id; // Importa la anotación para definir la clave primaria
import lombok.Data; // Importa la anotación de Lombok para generación automática de métodos

@Data // Genera automáticamente métodos getters, setters, equals, hashCode y toString
@Entity // Indica que esta clase es una entidad JPA
public class Proveedor {
    @Id // Define este campo como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que el valor se generará automáticamente
    private Integer Id; // ID del proveedor

    private String nombre; // Nombre del proveedor

    private String empresa; // Nombre de la empresa del proveedor

    private String direction; // Dirección del proveedor

    private Integer telefono; // Número de teléfono del proveedor
}
