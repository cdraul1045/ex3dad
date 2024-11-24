package com.example.msgatewayserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Anotación para un constructor sin argumentos (necesario para ciertas operaciones como deserialización JSON).
@NoArgsConstructor

// Anotación para crear un constructor con todos los argumentos (útil para crear instancias rápidamente).
@AllArgsConstructor

// Anotación para habilitar el patrón Builder, que permite construir objetos de forma fluida.
@Builder

// Anotación que genera automáticamente getters, setters, métodos toString, equals y hashCode.
@Data
public class TokenDto {
    // Campo que contiene el token de autenticación.
    private String token;
}