package com.example.mspedido.dto;

import lombok.Data;

@Data
public class
ProductoDto {
    private Integer id;
    private String nombre;
    private String modelo;
    private Integer codigo;
    private byte[] imagen;
}
