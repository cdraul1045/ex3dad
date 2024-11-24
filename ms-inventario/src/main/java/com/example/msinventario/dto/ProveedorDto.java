package com.example.msinventario.dto;

import lombok.Data; // Importa la anotación de Lombok para generar automáticamente getters y setters

@Data // Genera automáticamente los métodos getters, setters, toString, equals y hashCode
public class ProveedorDto {
    private Integer id; // ID único del proveedor

    private String nombre; // Nombre del proveedor

    private String empresa; // Nombre de

    private String direccion; // Dirección del proveedor

    private String telefono; // Número de teléfono del proveedor
}
