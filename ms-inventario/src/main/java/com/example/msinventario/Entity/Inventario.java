package com.example.msinventario.Entity;

import com.example.msinventario.dto.ProveedorDto; // Importa el DTO de Proveedor
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Para ignorar propiedades durante la serialización
import jakarta.persistence.*; // Importa anotaciones de JPA
import lombok.Data; // Importa la anotación de Lombok para generar automáticamente getters y setters

import java.util.List; // Importa la clase List para manejar listas

@Entity // Indica que esta clase es una entidad JPA
@Data // Genera automáticamente los métodos getters, setters, toString, equals y hashCode
public class Inventario {
    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor de ID
        private Integer id; // ID único del inventario

        private String nombre; // Nombre del inventario

        private String descripcion; // Descripción del inventario

        private Double stock; // Cantidad de productos en stock

        private String modelo; // Modelo del producto

        private Integer codigo; // Código único del producto

        private Integer proveedorId; // ID del proveedor relacionado

        // Constructor por defecto que inicializa stock a 0
        public Inventario() {
            this.stock = 0.0; // Inicializa el stock a 0.0
        }

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignora propiedades innecesarias durante la serialización
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Define una relación uno a muchos
    @JoinColumn(name = "inventario_id") // Nombre de la columna en la tabla hija que hace referencia a esta entidad
    private List<InventarioDetalle> inventarioDetalle; // Lista de detalles de inventario

    @Transient // Indica que este campo no se persiste en la base de datos
    private ProveedorDto proveedorDto; // Objeto Proveedor, no persistido en la base de datos
}
