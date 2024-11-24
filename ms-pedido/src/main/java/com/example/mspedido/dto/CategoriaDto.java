package com.example.mspedido.dto;

import lombok.Data;

@Data
public class CategoriaDto {
   private Integer id;
   private String franchise; // Franquicia a la que pertenecen los Funkos (e.g., "Marvel", "DC Comics")
   private String type; // Tipo de Funko (e.g., "Pop!", "Vinyl Soda")
   private Integer popularity; // Un valor numérico que indica la popularidad de la categoría
}
