package com.example.mspago.dto;

import lombok.Data;

@Data
public class ClienteDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
}
