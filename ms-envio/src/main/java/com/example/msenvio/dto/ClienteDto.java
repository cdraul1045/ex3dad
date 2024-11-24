package com.example.msenvio.dto;

import lombok.Data; // Importa la anotación Data de Lombok para generar métodos automáticamente

@Data // Genera automáticamente los métodos getters, setters, toString, equals y hashCode
public class ClienteDto {
    private Integer id; // Identificador único del cliente
    private String nombre; // Nombre del cliente
    private String apellido; // Apellido del cliente
    private String email; // Correo electrónico del cliente
    private String telefono; // Número de teléfono del cliente
    private String direccion; // Dirección del cliente
}
