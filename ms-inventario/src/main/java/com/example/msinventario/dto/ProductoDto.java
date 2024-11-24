package com.example.msinventario.dto;

import lombok.Data; // Importa la anotación de Lombok para generar automáticamente getters y setters

@Data // Genera automáticamente los métodos getters, setters, toString, equals y hashCode
public class ProductoDto {
    private Integer id; // ID único del producto

    private String nombre; // Nombre del producto

    private String modelo; // Modelo del producto

    private Integer codigo; // Código del producto, puede ser un SKU o un código interno

    private byte[] imagen;

}
