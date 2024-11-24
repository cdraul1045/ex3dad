package com.example.msenvio.dto;

import lombok.Data; // Importa la anotación Data de Lombok para generar métodos automáticamente

@Data // Genera automáticamente los métodos getters, setters, toString, equals y hashCode
public class ProductoDto {
    private Integer id; // Identificador único del producto
    private String nombre; // Nombre del producto
    private String modelo; // Modelo del producto
    private Integer codigo; // Código del producto (puede ser un código SKU o similar)
    private byte[] imagen;
}
