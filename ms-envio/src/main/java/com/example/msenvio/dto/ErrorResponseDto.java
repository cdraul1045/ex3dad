package com.example.msenvio.dto;

import lombok.AllArgsConstructor;  // Importa la anotación para generar un constructor con todos los parámetros
import lombok.Data;              // Importa la anotación para generar getters, setters, toString, etc.
import lombok.NoArgsConstructor;  // Importa la anotación para generar un constructor sin parámetros

@Data                         // Genera automáticamente getters y setters, y otros métodos útiles
@NoArgsConstructor           // Genera un constructor sin argumentos
@AllArgsConstructor          // Genera un constructor con todos los argumentos
public class ErrorResponseDto {
    private String message;  // Mensaje de error que se enviará en la respuesta
}
