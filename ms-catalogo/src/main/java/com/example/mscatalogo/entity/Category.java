package com.example.mscatalogo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String franchise; // Franquicia a la que pertenecen los Funkos (e.g., "Marvel", "DC Comics")
    private String type; // Tipo de Funko (e.g., "Pop!", "Vinyl Soda")
    private Integer popularity; // Un valor numérico que indica la popularidad de la categoría


}
